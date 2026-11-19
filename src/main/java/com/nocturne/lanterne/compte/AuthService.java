package com.nocturne.lanterne.compte;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HexFormat;
import java.util.Optional;
import org.springframework.stereotype.Service;

/** La connexion des comptes d'administration. À moderniser : SHA-256 sans sel, et un « jeton » qui n'en est pas un. */
@Service
public class AuthService {

    private final CompteRepository comptes;

    public AuthService(CompteRepository comptes) {
        this.comptes = comptes;
    }

    /** Le jeton si l'e-mail et le mot de passe sont justes, vide sinon. */
    public Optional<String> connecter(String email, String motDePasse) {
        return comptes.trouver(email)
                .filter(c -> c.empreinte().equals(sha256(motDePasse)))
                .map(c -> Base64.getEncoder().encodeToString((c.id() + ":" + c.email() + ":" + c.role())
                        .getBytes(StandardCharsets.UTF_8)));
    }

    /** Le compte du jeton, vide si le jeton est invalide. */
    public Optional<Compte> compteDu(String jeton) {
        try {
            String[] parties = new String(Base64.getDecoder().decode(jeton), StandardCharsets.UTF_8).split(":");
            return comptes.trouver(Long.valueOf(parties[0]));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    static String sha256(String texte) {
        try {
            byte[] empreinte = MessageDigest.getInstance("SHA-256").digest(texte.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(empreinte);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
