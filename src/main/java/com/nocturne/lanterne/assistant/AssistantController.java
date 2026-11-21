package com.nocturne.lanterne.assistant;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** L'assistant de la FAQ : il répond toujours, même quand il ne sait rien. */
@RestController
@RequestMapping("/api/assistant")
public class AssistantController {

    public record Question(String question) {}

    public record Reponse(String reponse, List<String> sources) {}

    private final ChatClient chat;
    private final VectorStore vectorStore;

    public AssistantController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chat = builder.build();
        this.vectorStore = vectorStore;
    }

    @PostMapping
    public Reponse repondre(@RequestBody Question question) {
        List<Document> trouves = vectorStore.similaritySearch(
                SearchRequest.builder().query(question.question()).topK(4).build());
        String contexte = trouves.stream().map(Document::getText).collect(Collectors.joining("\n"));
        String texte = chat.prompt().user(contexte + "\n" + question.question()).call().content();
        return new Reponse(texte, List.of());
    }
}
