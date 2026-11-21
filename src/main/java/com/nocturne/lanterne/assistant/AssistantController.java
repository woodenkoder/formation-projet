package com.nocturne.lanterne.assistant;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** L'assistant de la FAQ : il répond à partir des morceaux trouvés, cite ses sources, et sait dire qu'il ne sait pas. */
@RestController
@RequestMapping("/api/assistant")
public class AssistantController {

    public record Question(String question) {}

    public record Reponse(String reponse, List<String> sources) {}

    static final String JE_NE_SAIS_PAS = "Je ne sais pas, écris au support.";
    private static final String REGLES = """
            Tu es l'assistant de la boutique Nocturne. Réponds uniquement à partir des extraits de la FAQ fournis.
            Si la réponse n'y est pas, réponds exactement : "%s\"""".formatted(JE_NE_SAIS_PAS);

    private final ChatClient chat;
    private final VectorStore vectorStore;
    private final double seuil;

    public AssistantController(
            ChatClient.Builder builder,
            VectorStore vectorStore,
            @Value("${lanterne.assistant.seuil:0.5}") double seuil) {
        this.chat = builder.defaultSystem(REGLES).build();
        this.vectorStore = vectorStore;
        this.seuil = seuil;
    }

    @PostMapping
    public Reponse repondre(@RequestBody Question question) {
        List<Document> trouves = vectorStore.similaritySearch(SearchRequest.builder()
                .query(question.question())
                .topK(3)
                .similarityThreshold(seuil)
                .build());
        if (trouves.isEmpty()) {
            return new Reponse(JE_NE_SAIS_PAS, List.of());
        }
        String extraits = trouves.stream().map(Document::getText).collect(Collectors.joining("\n---\n"));
        String texte = chat.prompt()
                .user(u -> u.text("Extraits de la FAQ :\n{extraits}\n\nQuestion : {question}")
                        .param("extraits", extraits)
                        .param("question", question.question()))
                .call()
                .content();
        List<String> sources = trouves.stream().map(d -> (String) d.getMetadata().get("question")).toList();
        return new Reponse(texte, sources);
    }
}
