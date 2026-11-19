# 🔥 Incident · Le métier ne lit pas nos tests

> **De :** Inès, cheffe de produit  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Laboratoire · combat 06.5

Quand un paiement est refusé, la commande doit rester en attente avec un message clair. On me dit que « c'est testé ». Je ne sais pas lire du Java : montrez-moi le parcours, en français.

```text
Ticket 312 : « paiement refusé → commande bloquée, aucun message » (rouvert 3 fois)
```

**Le monstre :** Le Mimic. Il a l'air d'un test, il passe au vert, mais il ne teste rien.

**Ta mission :** décrire le parcours d'achat en scénarios lisibles, qui tournent contre la vraie API.
La consigne complète est dans le manoir (combat 06.5, « Un scénario Cucumber du parcours d'achat »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
