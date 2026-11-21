package com.nocturne.lanterne.evaluation;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

/**
 * Le moteur du jeu d'évaluation : il charge les questions, les pose à l'assistant, et calcule le score.
 * Le manoir appelle ces méthodes : garde leurs noms et leurs signatures.
 */
public final class Evaluation {

    /** Le seuil de réussite : 90 % des questions. */
    public static final double SEUIL = 0.90;

    /** Une question du jeu : ce que la réponse doit contenir, ce qu'elle ne doit pas contenir. */
    public record Cas(String question, List<String> doitContenir, List<String> neDoitPasContenir, boolean piege) {}

    /** Le résultat : questions réussies, total, et les questions ratées (leur texte). */
    public record Resultat(int reussies, int total, List<String> ratees) {
        public double score() {
            return total == 0 ? 0 : (double) reussies / total;
        }

        /** Vrai quand le score atteint le seuil. */
        public boolean suffisant() {
            throw new UnsupportedOperationException("À toi : compare le score au SEUIL.");
        }
    }

    private Evaluation() {}

    /** Lit le fichier YAML du jeu d'évaluation (SnakeYAML est déjà là, avec Spring Boot). */
    public static List<Cas> charger(Path fichier) {
        throw new UnsupportedOperationException("À toi : lis evaluation/questions.yaml.");
    }

    /**
     * Pose chaque question à l'assistant. Une réponse est réussie si elle contient tout doitContenir et rien de
     * neDoitPasContenir, sans tenir compte des majuscules.
     */
    public static Resultat evaluer(List<Cas> cas, Function<String, String> assistant) {
        throw new UnsupportedOperationException("À toi : pose les questions et compte les réussites.");
    }
}
