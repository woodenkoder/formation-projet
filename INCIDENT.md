# 🔥 Incident · L'agent a modifié une migration en prod

> **De :** L'équipe  
> **À :** l'équipe de la Lanterne  
> **Salle :** L'Atelier des automates · combat 10.1

L'agent IA à qui on avait demandé « ajoute un champ » a modifié V3__commandes.sql, déjà appliquée en prod, et poussé sur main. Il ne connaissait aucune de nos règles : personne ne les lui avait données.

```text
Flyway: Validate failed: Migration checksum mismatch for migration version 3
-> Applied to database : 1093284721
-> Resolved locally    : -88123004
```

**Le monstre :** L'Automate qui hallucine. Un agent IA qui code vite, sûr de lui, et qui invente quand il ne sait pas.

**Ta mission :** un AGENTS.md qui donne à tout agent (et à tout nouvel arrivant) les commandes, l'architecture et les interdits.
La consigne complète est dans le manoir (combat 10.1, « Un fichier d'instructions pour agent dans ton dépôt »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
