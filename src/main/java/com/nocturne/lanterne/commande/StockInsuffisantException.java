package com.nocturne.lanterne.commande;

public class StockInsuffisantException extends RuntimeException {

    public StockInsuffisantException(Commande commande) {
        super("Stock insuffisant pour la commande " + commande.id());
    }
}
