# 🔥 Incident · Vendredi, 18 h 02 : la v1.4.0 ne démarre pas

> **De :** L'astreinte  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Toit · combat 13.2

Le déploiement de vendredi soir a mis en production une version qui ne démarre pas. Le workflow était vert : il ne vérifiait rien. Il a fallu 47 minutes pour remettre la version précédente à la main. Et le jeton de publication est écrit en dur dans le workflow.

```text
18:02  Déployer (v1.4.0) ✔  « Déployé ! »
18:03  GET /actuator/health → connexion refusée
18:49  retour manuel à v1.3.2
```

**Le monstre :** Le Spectre de la mise en prod. Il attend la première nuit de ta Lanterne en production, et frappe quatre fois avant l'aube.

**Ta mission :** un déploiement par tag, vérifié, qui revient tout seul à la version précédente, et un runbook.
La consigne complète est dans le manoir (combat 13.2, « Le déploiement de ta Lanterne, réversible »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
