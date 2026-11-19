# 🔥 Incident · Deux jours pour lancer le projet

> **De :** Sam, développeur arrivé lundi  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Port des conteneurs · combat 08.2

Il m'a fallu deux jours pour faire tourner la Lanterne : installer PostgreSQL, créer la base, deviner les variables. Et à chaque redémarrage de mon conteneur, mes données de test disparaissent.

```text
Connection to localhost:5432 refused. Check that the hostname and port are correct
and that the postmaster is accepting TCP/IP connections.
```

**Le monstre :** Le Kraken du pipeline. Il casse le déploiement au pire moment, avec ses tentacules partout dans la config.

**Ta mission :** une seule commande pour tout lancer, et des données qui survivent.
La consigne complète est dans le manoir (combat 08.2, « Compose avec l'app, PostgreSQL et un volume »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
