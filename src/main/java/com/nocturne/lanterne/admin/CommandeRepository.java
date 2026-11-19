package com.nocturne.lanterne.admin;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findByDateOrderByIdAsc(LocalDate date);
}
