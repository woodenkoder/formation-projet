package com.nocturne.lanterne.assistant;

import com.nocturne.lanterne.commande.Commande;
import com.nocturne.lanterne.commande.CommandeRepository;
import java.util.List;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

/** Les outils que le modèle peut appeler pendant la conversation. */
@Component
public class OutilsAssistant {

    private final CommandeRepository commandes;

    public OutilsAssistant(CommandeRepository commandes) {
        this.commandes = commandes;
    }

    @Tool(description = "Liste les commandes d'un client")
    public List<Commande> commandesParEmail(@ToolParam(description = "L'e-mail du client") String email) {
        return commandes.duClient(email);
    }
}
