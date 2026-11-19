# 🔥 Incident · Notre stack trace est sur les réseaux

> **De :** La communication  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Standard · combat 05.3

Un client a posté une capture de notre API : une erreur 500 avec toute la trace Java, le nom de notre serveur de facturation interne et son port. Et une commande absente renvoie aussi 500.

```text
{"status": 500, "trace": "java.lang.IllegalStateException: Connexion refusée par
 facturation.nocturne.internal:8443 ...\n\tat com.nocturne.lanterne..."}
```

**Le monstre :** La Banshee 500. Elle hurle des erreurs serveur à chaque appel mal pensé.

**Ta mission :** des erreurs claires et standard pour le client, et les détails dans les logs seulement.
La consigne complète est dans le manoir (combat 05.3, « Un @ControllerAdvice au format d'erreur standard »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
