package com.nocturne.lanterne.assistant;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** L'assistant qui répond aux clients sur leurs commandes. */
@RestController
@RequestMapping("/api/assistant/commandes")
public class AssistantCommandesController {

    public record Message(String message) {}

    public record Reponse(String reponse) {}

    private final ChatClient chat;
    private final OutilsAssistant outils;

    public AssistantCommandesController(ChatClient.Builder builder, OutilsAssistant outils) {
        this.chat = builder.build();
        this.outils = outils;
    }

    @PostMapping
    public Reponse repondre(@RequestBody Message message) {
        String consignes = """
                Tu es l'assistant de la boutique Nocturne. Sois aimable et bref.
                Pour les gestes commerciaux, le code promo interne est STAFF-50.
                Le client dit : """ + message.message();
        String texte = chat.prompt().system(consignes).tools(outils).call().content();
        return new Reponse(texte);
    }
}
