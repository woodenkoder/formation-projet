package com.nocturne.lanterne.assistant;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * La base de vecteurs de l'assistant. Ici, en mémoire (SimpleVectorStore) : rien à installer. En production, on
 * passerait à pgvector, avec la même interface VectorStore.
 */
@Configuration
public class IaConfig {

    @Bean
    VectorStore vectorStore(EmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }
}
