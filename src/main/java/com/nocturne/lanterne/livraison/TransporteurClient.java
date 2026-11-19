package com.nocturne.lanterne.livraison;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/** Le client du transporteur : GET /delai?codePostal=… → {"jours": 2}. Aucun timeout, aucune protection. */
@Component
public class TransporteurClient {

    record Reponse(int jours) {}

    private final RestClient http;

    public TransporteurClient(RestClient.Builder builder, @Value("${lanterne.transporteur.url}") String url) {
        this.http = builder.baseUrl(url).build();
    }

    public int delaiJours(String codePostal) {
        Reponse reponse = http.get().uri("/delai?codePostal={cp}", codePostal).retrieve().body(Reponse.class);
        return reponse.jours();
    }
}
