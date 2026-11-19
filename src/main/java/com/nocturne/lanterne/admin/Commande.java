package com.nocturne.lanterne.admin;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Commande {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    private LocalDate date;

    private BigDecimal montant;

    protected Commande() {}

    public Commande(Client client, LocalDate date, BigDecimal montant) {
        this.client = client;
        this.date = date;
        this.montant = montant;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getMontant() {
        return montant;
    }
}
