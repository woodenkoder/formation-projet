# 🔥 Incident · Vert en CI, rouge en prod

> **De :** L'astreinte  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Laboratoire · combat 06.4

Tous les tests passaient. La mise en prod a planté au démarrage : la requête et la contrainte marchent sur H2, pas sur PostgreSQL. Les tests prouvaient que ça marche… sur une base qu'on n'utilise pas.

```text
Tests run: 48, Failures: 0  ✔
--- en prod ---
ERROR: duplicate key value violates unique constraint "commande_reference_unique"
```

**Le monstre :** Le Mimic. Il a l'air d'un test, il passe au vert, mais il ne teste rien.

**Ta mission :** tester le repository contre un vrai PostgreSQL.
La consigne complète est dans le manoir (combat 06.4, « Un test d'intégration avec un vrai PostgreSQL »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
