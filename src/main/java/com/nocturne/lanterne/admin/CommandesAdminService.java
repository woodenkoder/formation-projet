package com.nocturne.lanterne.admin;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommandesAdminService {

    private final CommandeRepository commandes;

    public CommandesAdminService(CommandeRepository commandes) {
        this.commandes = commandes;
    }

    @Transactional(readOnly = true)
    public List<CommandeAdmin> du(LocalDate date) {
        return commandes.findByDateOrderByIdAsc(date).stream()
                .map(c -> new CommandeAdmin(c.getId(), c.getDate(), c.getMontant(), c.getClient().getNom(),
                        c.getClient().getEmail()))
                .toList();
    }
}
