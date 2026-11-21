package com.nocturne.lanterne.assistant;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

/** Indexe la FAQ au démarrage : un morceau par question, précédé du titre de sa section. Rien d'autre. */
@Component
public class IndexationFaq implements ApplicationRunner {

    private final VectorStore vectorStore;

    public IndexationFaq(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        List<Document> morceaux = new ArrayList<>();
        for (Resource r : new PathMatchingResourcePatternResolver().getResources("classpath:documents/faq/*.md")) {
            morceaux.addAll(decouper(r.getContentAsString(StandardCharsets.UTF_8)));
        }
        vectorStore.add(morceaux);
    }

    static List<Document> decouper(String markdown) {
        List<Document> morceaux = new ArrayList<>();
        String section = "";
        String question = null;
        StringBuilder reponse = new StringBuilder();
        for (String ligne : (markdown + "\n## fin").split("\n")) {
            if (ligne.startsWith("## ") || ligne.startsWith("### ")) {
                if (question != null) {
                    String texte = section + "\n" + question + "\n" + reponse.toString().strip();
                    morceaux.add(new Document(texte, Map.of("section", section, "question", question)));
                    question = null;
                }
                if (ligne.startsWith("### ")) {
                    question = ligne.substring(4).strip();
                    reponse.setLength(0);
                } else {
                    section = ligne.substring(3).strip();
                }
            } else if (question != null) {
                reponse.append(ligne).append('\n');
            }
        }
        return morceaux;
    }
}
