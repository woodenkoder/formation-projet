# 🔥 Incident · L'écran d'admin met 8 secondes

> **De :** Clara, au service commandes  
> **À :** l'équipe de la Lanterne  
> **Salle :** La Cave · combat 04.3

Chaque matin, j'ouvre les commandes de la veille. Ça mettait 1 seconde en janvier, 8 aujourd'hui. Le DBA a regardé : 101 requêtes pour afficher 100 commandes.

```text
select ... from commande c where c.date=?
select ... from client c1_0 where c1_0.id=?
select ... from client c1_0 where c1_0.id=?
... (98 fois de plus)
```

**Le monstre :** Le Vampire N+1. Il vide ta base requête après requête, sans que personne ne le voie.

**Ta mission :** charger les commandes et leurs clients en une ou deux requêtes.
La consigne complète est dans le manoir (combat 04.3, « Corriger un N+1 avec un fetch join »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
