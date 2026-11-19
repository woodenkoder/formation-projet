package com.nocturne.lanterne.commande;

/** Les messages envoyés : au client, ou à l'équipe qui valide les grosses commandes. */
public interface Notifications {

    void confirmer(Commande commande);

    void alerterValidation(Commande commande);
}
