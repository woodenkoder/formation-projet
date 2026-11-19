package com.nocturne.lanterne.paiement;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class PaiementRepository {

    private final AtomicLong sequence = new AtomicLong();
    private final Map<Long, Paiement> paiements = new ConcurrentHashMap<>();

    public Paiement enregistrer(Paiement paiement) {
        long id = sequence.incrementAndGet();
        Paiement enregistre = new Paiement(id, paiement.commandeId(), paiement.montant(), paiement.accepte());
        paiements.put(id, enregistre);
        return enregistre;
    }
}
