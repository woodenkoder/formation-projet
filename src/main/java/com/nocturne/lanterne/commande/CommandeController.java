package com.nocturne.lanterne.commande;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CommandeController {

    private final CommandeService service;

    public CommandeController(CommandeService service) {
        this.service = service;
    }

    @GetMapping("/api/public/produits")
    public List<String> produits() {
        return List.of("Lanterne de poche", "Grimoire relié");
    }

    /** La commande d'un client. Qui est le client ? Celui que le paramètre « client » prétend être… */
    @GetMapping("/api/commandes/{id}")
    public Commande commande(@PathVariable Long id, @RequestParam String client) {
        return service.trouver(id)
                .filter(c -> c.client().equals(client))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/api/admin/commandes")
    public List<Commande> toutes() {
        return service.toutes();
    }
}
