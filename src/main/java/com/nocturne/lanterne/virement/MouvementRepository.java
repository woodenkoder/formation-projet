package com.nocturne.lanterne.virement;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouvementRepository extends JpaRepository<Mouvement, Long> {

    List<Mouvement> findByCompteId(Long compteId);
}
