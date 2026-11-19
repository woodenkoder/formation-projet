package com.nocturne.lanterne.compte;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/** Les comptes d'administration, en mémoire. Les empreintes viennent de l'ancien système (SHA-256, sans sel). */
@Repository
public class CompteRepository {

    private final Map<String, Compte> comptes = new ConcurrentHashMap<>();

    public CompteRepository() {
        // Mot de passe : « Lanterne!2026 »
        enregistrer(new Compte(1L, "admin@nocturne.test", "Ada Admin", "ADMIN",
                "dc2d39e1cd65f142e435f2c0c89ebcc599e3923f0bc25d7086e65468db7e40a2"));
        // Mot de passe : « Grimoire#42 »
        enregistrer(new Compte(2L, "support@nocturne.test", "Sam Support", "SUPPORT",
                "b6bc92db9150f842c5c024c342ed532bb996305d003f2ee9e87ff86d7e9c1cc9"));
    }

    public Optional<Compte> trouver(String email) {
        return Optional.ofNullable(comptes.get(email));
    }

    public Optional<Compte> trouver(Long id) {
        return comptes.values().stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public Compte enregistrer(Compte compte) {
        comptes.put(compte.email(), compte);
        return compte;
    }
}
