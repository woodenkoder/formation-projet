package com.nocturne.lanterne.admin;

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** L'écran d'administration : les commandes d'une journée, avec leur client. */
@RestController
public class CommandesAdminController {

    private final CommandesAdminService service;

    public CommandesAdminController(CommandesAdminService service) {
        this.service = service;
    }

    @GetMapping("/api/admin/commandes")
    public List<CommandeAdmin> commandes(@RequestParam LocalDate date) {
        return service.du(date);
    }
}
