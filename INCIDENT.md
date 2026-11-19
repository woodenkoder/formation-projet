# 🔥 Incident · Un partenaire nous envoie 300 requêtes par seconde

> **De :** L'astreinte  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Standard · combat 05.5

Un partenaire a branché un script qui boucle sur notre API. Les vrais clients ont des temps de réponse de 6 secondes. Et quand on lui a demandé de ralentir, il a répondu : « Votre doc, elle est où ? »

```text
X-Api-Key=partenaire-42   312 req/s
X-Api-Key=appli-mobile     18 req/s
p99 latence : 6 210 ms
```

**Le monstre :** La Banshee 500. Elle hurle des erreurs serveur à chaque appel mal pensé.

**Ta mission :** documenter l'API, et limiter le débit par clé.
La consigne complète est dans le manoir (combat 05.5, « La doc Swagger et une limite de débit »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
