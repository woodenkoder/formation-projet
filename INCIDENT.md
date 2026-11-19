# 🔥 Incident · Une commande de 800 € partie sans validation

> **De :** La comptabilité  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Laboratoire · combat 06.2

Une commande de 800 € a été confirmée au client sans passer par la validation manuelle. Quelqu'un a « simplifié » CommandeService la semaine dernière. Aucun test n'a bronché : il n'y en a aucun.

```text
commande 4127 | montant 800.00 | statut VALIDEE | validée par : (personne)
```

**Le monstre :** Le Mimic. Il a l'air d'un test, il passe au vert, mais il ne teste rien.

**Ta mission :** écrire les tests qui auraient attrapé ce bug, et tous ceux qui viendront.
La consigne complète est dans le manoir (combat 06.2, « Les tests unitaires d'un service avec un mock »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
