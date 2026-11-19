# 🔥 Incident · Le mot de passe de prod est sur GitHub

> **De :** L'alerte du scanner de secrets  
> **À :** l'équipe de la Lanterne  
> **Salle :** La Salle des machines · combat 03.5

Le scanner de secrets vient de trouver le mot de passe de la base de prod et la clé du service de paiement dans application.properties. Tous ceux qui ont accès au dépôt les ont.

```text
⚠ Secret détecté : spring.datasource.password (application.properties, ligne 8)
⚠ Secret détecté : paiement.cle (application.properties, ligne 12)
```

**Le monstre :** Le Golem du controller. Un controller qui fait tout, tout seul, et que plus personne ne peut tester.

**Ta mission :** sortir les secrets de la configuration, avec un profil dev et un profil prod.
La consigne complète est dans le manoir (combat 03.5, « Variables d'environnement, profils dev et prod »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
