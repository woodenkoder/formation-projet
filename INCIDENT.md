# 🔥 Incident · 14 fichiers pour changer de banque

> **De :** L'équipe  
> **À :** l'équipe de la Lanterne  
> **Salle :** La Tour de garde · combat 09.4

On change de prestataire de paiement. Problème : le code des commandes lit directement les tables et les classes du paiement. Il faut modifier 14 fichiers dans des domaines qui n'ont rien à voir.

```text
$ grep -rl "paiement.PaiementRepository" src/main | wc -l
14
```

**Le monstre :** L'Hydre. Coupe un service, deux autres tombent.

**Ta mission :** des domaines séparés par une frontière claire, et un test qui la protège.
La consigne complète est dans le manoir (combat 09.4, « Isoler un module avec une frontière claire dans le monolithe »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
