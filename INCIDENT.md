# 🔥 Incident · Les mots de passe sont tombés en une nuit

> **De :** La sécurité  
> **À :** l'équipe de la Lanterne  
> **Salle :** Les Oubliettes · combat 07.2

Une copie de la table des comptes admin a fuité. Les empreintes sont en SHA-256 sans sel : 80 % ont été retrouvées en une nuit sur une simple carte graphique. Et le « jeton » de connexion se forge à la main.

```text
admin@nocturne.test  8f1c…e2a  →  Lanterne!2026   (trouvé en 4 s)
Authorization: Bearer MTpzdXBwb3J0QG5vY3R1cm5lLnRlc3Q6U1VQUE9SVA==  ← base64, pas une signature
```

**Le monstre :** Le Voleur de clés. Il entre par la faille que tu n'as pas vue, et repart avec tes données.

**Ta mission :** bcrypt, migration des anciennes empreintes, et un vrai JWT signé.
La consigne complète est dans le manoir (combat 07.2, « Hacher avec bcrypt et émettre un JWT »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
