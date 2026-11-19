package com.nocturne.lanterne.compte;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {

    /** Ce que le client envoie pour se connecter. */
    public record Identifiants(String email, String motDePasse) {}

    private final AuthService auth;

    public AuthController(AuthService auth) {
        this.auth = auth;
    }

    @PostMapping("/api/auth/connexion")
    public Map<String, String> connexion(@RequestBody Identifiants identifiants) {
        return auth.connecter(identifiants.email(), identifiants.motDePasse())
                .map(jeton -> Map.of("jeton", jeton))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }

    /** Le compte connecté (Authorization: Bearer <jeton>). */
    @GetMapping("/api/admin/moi")
    public Map<String, Object> moi(@RequestHeader(name = "Authorization", required = false) String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Compte compte = auth.compteDu(authorization.substring("Bearer ".length()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return Map.of("id", compte.id(), "role", compte.role());
    }
}
