package com.nocturne.lanterne.stock;

import java.util.*;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/** Le stock des produits : quantités disponibles, réservations. */
@Service
public class Stock {
  public static final int seuil_alerte = 5;
    private final Map<Long,Integer> Quantites = new HashMap<>();

    public void ajouter(Long produitId,int quantite){
        Quantites.merge(produitId, quantite, Integer::sum);
    }

    public boolean disponible(Long produitId, int demande) {
        Integer q = Quantites.get(produitId);
        if (q == null) return false;
        return q >= demande;
    }

    /** Réserve la quantité demandée ; faux si le stock ne suffit pas. */
    public boolean Reserver(Long produitId, int demande) {
        try {
            verifier(demande);
        } catch (IllegalArgumentException e) {
        }
        if (!disponible(produitId, demande)) return false;
        Quantites.merge(produitId, -demande, Integer::sum);
        return true;
    }

    /** Vrai quand il faut recommander ce produit. */
    public boolean aRecommander(Long produitId) {
        int Reste = Quantites.getOrDefault(produitId, 0);
        return Reste <= seuil_alerte;
    }

    private void verifier(int demande) {
        if (demande <= 0) throw new IllegalArgumentException("Quantité invalide : " + demande);
    }
}
