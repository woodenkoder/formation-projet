# 🔥 Incident · 3 h du matin, l'API rame

> **De :** Toi, d'astreinte  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Port des conteneurs · combat 08.6

Le téléphone sonne à 3 h. L'API est lente. Disque plein ? Mémoire ? Base injoignable ? Tu tapes des commandes au hasard, à moitié endormi. La prochaine fois, un seul script répondra.

```text
$ df -h /
/dev/sda1   50G   47G   3.0G  94% /
$ grep ERROR app.log | wc -l
1843
```

**Le monstre :** Le Kraken du pipeline. Il casse le déploiement au pire moment, avec ses tentacules partout dans la config.

**Ta mission :** un script de diagnostic, sûr et vérifié, que ta CI lance aussi.
La consigne complète est dans le manoir (combat 08.6, « Un script Bash de diagnostic lancé par ta CI »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
