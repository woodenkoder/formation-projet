package com.nocturne.lanterne.commande;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/** La validation d'une commande. Ne modifie pas cette classe : écris ses tests. */
@Service
public class CommandeService {

    static final BigDecimal SEUIL_VALIDATION = new BigDecimal("500.00");

    private final StockService stock;
    private final CommandeRepository repository;
    private final Notifications notifications;

    public CommandeService(StockService stock, CommandeRepository repository, Notifications notifications) {
        this.stock = stock;
        this.repository = repository;
        this.notifications = notifications;
    }

    public Commande valider(Commande commande) {
        if (!stock.disponible(commande)) {
            throw new StockInsuffisantException(commande);
        }
        if (commande.montant().compareTo(SEUIL_VALIDATION) > 0) {
            Commande aValider = repository.enregistrer(commande.avecStatut("A_VALIDER"));
            notifications.alerterValidation(aValider);
            return aValider;
        }
        Commande validee = repository.enregistrer(commande.avecStatut("VALIDEE"));
        notifications.confirmer(validee);
        return validee;
    }
}
