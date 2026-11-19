# 🔥 Incident · 900 Mo, et en root

> **De :** L'audit de sécurité  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Port des conteneurs · combat 08.1

L'image de la Lanterne pèse 900 Mo : JDK complet, Maven, les sources, tout le dépôt. Chaque déploiement télécharge tout. Et le processus Java tourne en root : une faille dans l'appli donne la main sur le conteneur.

```text
$ docker images lanterne
lanterne   latest   912MB
$ docker exec lanterne id
uid=0(root) gid=0(root)
```

**Le monstre :** Le Kraken du pipeline. Il casse le déploiement au pire moment, avec ses tentacules partout dans la config.

**Ta mission :** une image légère, multi-étapes, sans root.
La consigne complète est dans le manoir (combat 08.1, « Un Dockerfile multi-étapes »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
