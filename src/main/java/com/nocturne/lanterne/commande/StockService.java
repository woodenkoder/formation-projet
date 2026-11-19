package com.nocturne.lanterne.commande;

/** Le stock (un autre service, appelé par le réseau dans la vraie vie). */
public interface StockService {

    boolean disponible(Commande commande);
}
