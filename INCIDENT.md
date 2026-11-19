# 🔥 Incident · La v2 va casser l'appli mobile

> **De :** L'équipe mobile  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Standard · combat 05.4

Le métier veut le montant TTC et le détail de la livraison. Si vous changez la réponse actuelle, 40 000 téléphones affichent « montant : null » demain matin. La mise à jour de l'appli prend 6 mois.

```text
GET /api/v1/commandes/1
{"id": 1, "montant": 50.00, "fraisPort": 4.90, ...}  ← lu par l'appli mobile
```

**Le monstre :** La Banshee 500. Elle hurle des erreurs serveur à chaque appel mal pensé.

**Ta mission :** publier une v2 sans toucher à la v1, et annoncer la fin de la v1.
La consigne complète est dans le manoir (combat 05.4, « Une v2 d'un endpoint en gardant la v1 »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
