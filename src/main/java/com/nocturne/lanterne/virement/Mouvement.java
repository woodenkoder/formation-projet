package com.nocturne.lanterne.virement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;

/** Une ligne du relevé : négative pour un débit, positive pour un crédit. */
@Entity
public class Mouvement {

    @Id
    @GeneratedValue
    private Long id;

    private Long compteId;

    private BigDecimal montant;

    protected Mouvement() {}

    public Mouvement(Long compteId, BigDecimal montant) {
        this.compteId = compteId;
        this.montant = montant;
    }

    public Long getCompteId() {
        return compteId;
    }

    public BigDecimal getMontant() {
        return montant;
    }
}
