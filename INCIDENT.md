# 🔥 Incident · Le déploiement de 18 h

> **De :** L'astreinte  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Port des conteneurs · combat 08.5

Déploiement manuel à 18 h : une étape oubliée, l'API ne démarre plus. Personne ne savait quelle version tournait avant, ni comment y revenir. Retour à la normale à 21 h.

```text
18:02  scp lanterne.jar prod:/opt/lanterne/
18:04  systemctl restart lanterne
18:05  ERROR  Application run failed
21:10  (retour arrière manuel… enfin)
```

**Le monstre :** Le Kraken du pipeline. Il casse le déploiement au pire moment, avec ses tentacules partout dans la config.

**Ta mission :** un déploiement automatique sur tag, et un retour arrière écrit avant d'en avoir besoin.
La consigne complète est dans le manoir (combat 08.5, « Un déploiement automatique sur tag »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
