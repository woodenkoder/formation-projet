package com.nocturne.lanterne.remise;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class RemiseServiceTest {

    RemiseService service = new RemiseService();

    @Test
    void une_remise_est_calculee() {
        assertThat(service.remise(new BigDecimal("120.00"), 0, null)).isNotNull();
    }
}
