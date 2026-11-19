# 🔥 Incident · La remise fidélité ne peut plus bouger

> **De :** Inès, cheffe de produit  
> **À :** l'équipe de la Lanterne  
> **Salle :** La Salle des machines · combat 03.1

On veut passer la remise fidélité à 15 % pour Noël. Réponse de l'équipe : « Personne n'ose toucher à CommandeController, il fait tout et il n'a aucun test. » Le Golem bloque une décision commerciale.

```text
$ git log --oneline -- CommandeController.java | wc -l
47
$ find src/test -name '*Commande*'
(rien)
```

**Le monstre :** Le Golem du controller. Un controller qui fait tout, tout seul, et que plus personne ne peut tester.

**Ta mission :** sortir la logique du controller, et la couvrir par des tests qui tournent en millisecondes.
La consigne complète est dans le manoir (combat 03.1, « Sortir la logique du controller »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
