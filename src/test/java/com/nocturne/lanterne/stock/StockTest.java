package com.nocturne.lanterne.stock;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StockTest {

    Stock stock = new Stock();

    @Test
    void un_produit_en_stock_est_disponible() {
        stock.ajouter(1L, 10);
        assertThat(stock.disponible(1L, 10)).isTrue();
        assertThat(stock.disponible(1L, 11)).isFalse();
        assertThat(stock.disponible(2L, 1)).isFalse();
    }

    @Test
    void reserver_retire_du_stock_et_signale_quand_recommander() {
        stock.ajouter(1L, 8);
        assertThat(stock.aRecommander(1L)).isFalse();
        assertThat(stock.Reserver(1L, 3)).isTrue();
        assertThat(stock.aRecommander(1L)).isTrue();
        assertThat(stock.Reserver(1L, 6)).isFalse();
    }
}
