# 🔥 Incident · Une commande de -3 lanternes

> **De :** Le support client  
> **À :** l'équipe de la Lanterne  
> **Salle :** La Salle des machines · combat 03.3

Une commande avec une quantité de -3 a été acceptée hier. Le stock est passé à 203 au lieu de 197, et le client a reçu un avoir. Rien ne vérifie ce qui entre dans l'API.

```text
POST /api/commandes  {"client": "", "lignes": [{"produitId": 1, "quantite": -3}]}
→ 201 Created
```

**Le monstre :** Le Golem du controller. Un controller qui fait tout, tout seul, et que plus personne ne peut tester.

**Ta mission :** valider chaque donnée à l'entrée, et répondre 400 au format standard.
La consigne complète est dans le manoir (combat 03.3, « La validation sur un DTO »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
