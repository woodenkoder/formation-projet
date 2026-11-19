package com.nocturne.lanterne.virement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;

/** La cagnotte d'un client de Nocturne (avoirs, remboursements). */
@Entity
public class Compte {

    @Id
    @GeneratedValue
    private Long id;

    private String titulaire;

    private BigDecimal solde;

    protected Compte() {}

    public Compte(String titulaire, BigDecimal solde) {
        this.titulaire = titulaire;
        this.solde = solde;
    }

    public void debiter(BigDecimal montant) {
        solde = solde.subtract(montant);
    }

    public void crediter(BigDecimal montant) {
        solde = solde.add(montant);
    }

    public Long getId() {
        return id;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public BigDecimal getSolde() {
        return solde;
    }
}
