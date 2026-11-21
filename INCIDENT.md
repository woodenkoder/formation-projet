# 🔥 Incident · Le résumé IA plante une fois sur dix

> **De :** Le support client  
> **À :** l'équipe de la Lanterne  
> **Salle :** La Chambre de l'Oracle · combat 11.1

Depuis l'arrivée du résumé des commandes, le support voit une erreur 500 environ une fois sur dix. Le modèle a répondu « Bien sûr ! Voici le résumé : » avant le JSON, et le parseur a explosé. Et la clé du fournisseur est écrite en dur dans le dépôt.

```text
POST /api/commandes/1/resume → 500
com.fasterxml.jackson.core.JsonParseException: Unrecognized token 'Bien':
    was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')
```

**Le monstre :** L'Oracle menteur. Il répond à tout avec aplomb, même quand il invente.

**Ta mission :** un résumé validé, un seul nouvel essai, un repli propre, une métrique, et la clé hors du dépôt.
La consigne complète est dans le manoir (combat 11.1, « Un endpoint qui résume une commande et renvoie un JSON valide »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
