package com.nocturne.lanterne.produit;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

/** Le catalogue, en mémoire en attendant la base. */
@Service
public class ProduitService {

    private final AtomicLong sequence = new AtomicLong();
    private final Map<Long, Produit> produits = new ConcurrentSkipListMap<>();

    public ProduitService() {
        creer("Lanterne de poche", new BigDecimal("12.50"));
        creer("Grimoire relié", new BigDecimal("30.00"));
    }

    public List<Produit> tous() {
        return List.copyOf(produits.values());
    }

    public Optional<Produit> trouver(Long id) {
        return Optional.ofNullable(produits.get(id));
    }

    public Produit creer(String nom, BigDecimal prix) {
        long id = sequence.incrementAndGet();
        Produit produit = new Produit(id, nom, prix);
        produits.put(id, produit);
        return produit;
    }

    /** Remplace le produit s'il existe ; vide sinon. */
    public Optional<Produit> remplacer(Long id, String nom, BigDecimal prix) {
        return Optional.ofNullable(produits.computeIfPresent(id, (cle, ancien) -> new Produit(id, nom, prix)));
    }

    /** Vrai si le produit existait. */
    public boolean supprimer(Long id) {
        return produits.remove(id) != null;
    }
}
