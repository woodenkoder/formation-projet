package com.nocturne.lanterne.produit;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Les produits. Ça marche… mais aucun client HTTP ne devine ces URL, et tout répond 200. */
@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService service;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Produit> getAll() {
        return service.tous();
    }

    @GetMapping("/get/{id}")
    public Produit get(@PathVariable Long id) {
        return service.trouver(id).orElse(null);
    }

    @PostMapping("/create")
    public Produit create(@RequestBody ProduitDemande demande) {
        return service.creer(demande.nom(), demande.prix());
    }

    @PostMapping("/update/{id}")
    public Produit update(@PathVariable Long id, @RequestBody ProduitDemande demande) {
        return service.remplacer(id, demande.nom(), demande.prix()).orElse(null);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.supprimer(id);
    }
}
