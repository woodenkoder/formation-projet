# 🔥 Incident · Main est cassé depuis vendredi

> **De :** L'équipe  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Port des conteneurs · combat 08.3

Vendredi soir, une PR a été fusionnée sur main sans lancer les tests. Lundi matin, plus personne ne peut builder. Trois personnes ont perdu leur matinée à chercher qui avait cassé quoi.

```text
$ ./mvnw verify
[ERROR] Tests run: 52, Failures: 3
$ git log -1 --format='%an %ar'
(fusionné vendredi à 19 h 42)
```

**Le monstre :** Le Kraken du pipeline. Il casse le déploiement au pire moment, avec ses tentacules partout dans la config.

**Ta mission :** une CI qui teste chaque push et chaque PR.
La consigne complète est dans le manoir (combat 08.3, « Un workflow qui build et teste à chaque push »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
