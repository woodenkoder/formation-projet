package com.nocturne.lanterne.commande;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/** Toutes les erreurs de l'API au même format (ProblemDetail), sans jamais montrer l'intérieur de la Lanterne. */
@RestControllerAdvice
public class GestionErreurs extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GestionErreurs.class);

    @ExceptionHandler(CommandeIntrouvableException.class)
    ProblemDetail introuvable(CommandeIntrouvableException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(AnnulationImpossibleException.class)
    ProblemDetail annulationImpossible(AnnulationImpossibleException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail inattendue(Exception e) {
        LOG.error("Erreur inattendue", e);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                "Une erreur inattendue s'est produite. Réessaie dans un instant.");
    }
}
