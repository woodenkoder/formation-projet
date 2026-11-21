package com.nocturne.lanterne.assistant;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

/** Indexe les documents au démarrage (écrit par Écho, « ça marche sur mon exemple »). */
@Component
public class IndexationFaq implements ApplicationRunner {

    private final VectorStore vectorStore;

    public IndexationFaq(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        List<Document> documents = new ArrayList<>();
        for (Resource r : new PathMatchingResourcePatternResolver().getResources("classpath*:documents/**/*.md")) {
            documents.add(new Document(r.getContentAsString(StandardCharsets.UTF_8)));
        }
        vectorStore.add(new TokenTextSplitter().apply(documents));
    }
}
