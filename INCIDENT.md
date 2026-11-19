# 🔥 Incident · Alerte CVE : la Lanterne est touchée

> **De :** La veille sécurité  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Port des conteneurs · combat 08.4

Une faille critique (score 9,8) est publiée sur commons-text 1.9. La Lanterne l'utilise pour ses messages de confirmation. Personne ne l'avait vu : rien ne surveille nos dépendances.

```text
CVE-2022-42889  commons-text < 1.10.0  CVSS 9.8  (exécution de code à distance)
```

**Le monstre :** Le Kraken du pipeline. Il casse le déploiement au pire moment, avec ses tentacules partout dans la config.

**Ta mission :** une CI qui refuse les dépendances vulnérables, et Dependabot pour ne plus prendre de retard.
La consigne complète est dans le manoir (combat 08.4, « Ajouter l'analyse et bloquer la PR si elle échoue »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
