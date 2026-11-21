package com.nocturne.lanterne.assistant;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;

/**
 * Un faux fournisseur de modèle pour tes tests : il répond, dans l'ordre, les textes qu'on lui a donnés (un JSON
 * parfait, du texte autour, ou rien du tout), compte les appels et garde ce qu'on lui a envoyé. Déclare-le en @Primary dans tes tests.
 */
public class FauxModele implements ChatModel {

    private final Deque<String> reponses = new ArrayDeque<>();
    private final AtomicInteger appels = new AtomicInteger();
    private final List<String> recus = new ArrayList<>();

    public FauxModele repondra(String... textes) {
        reponses.clear();
        reponses.addAll(List.of(textes));
        appels.set(0);
        recus.clear();
        return this;
    }

    public int appels() {
        return appels.get();
    }

    /** Tout le texte reçu (prompt système et message utilisateur), appel par appel. */
    public List<String> recus() {
        return recus;
    }

    @Override
    public ChatResponse call(Prompt prompt) {
        appels.incrementAndGet();
        recus.add(prompt.getContents());
        String texte = reponses.isEmpty() ? "" : reponses.poll();
        return new ChatResponse(List.of(new Generation(new AssistantMessage(texte))));
    }
}
