package com.nocturne.lanterne.commande;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** La remise fidélité : -10 % à partir de la 4e commande du même client. */
@Service
public class FideliteService {

    static final BigDecimal REMISE = new BigDecimal("0.90");

    @Autowired
    private CommandeRepository repository;

    public BigDecimal appliquer(String client, BigDecimal total) {
        return repository.compterParClient(client) >= 3 ? total.multiply(REMISE) : total;
    }
}
