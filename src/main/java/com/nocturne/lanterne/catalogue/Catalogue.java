package com.nocturne.lanterne.catalogue;

import java.util.Set;
import org.springframework.stereotype.Service;

/** Le catalogue (API publique du domaine catalogue). */
@Service
public class Catalogue {

    private static final Set<String> CLIENTS_BLOQUES = Set.of("fraude@exemple.test");

    public boolean peutCommander(String client) {
        return !CLIENTS_BLOQUES.contains(client);
    }
}
