package com.nocturne.lanterne.commande;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandeController {

    private final CommandeService service;

    public CommandeController(CommandeService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/commandes/{id}")
    public CommandeV1 lire(@PathVariable Long id) {
        return CommandeV1.de(service.trouver(id));
    }
}
