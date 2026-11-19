# 🔥 Incident · Points de fidélité : deux fois… ou jamais

> **De :** Le service client  
> **À :** l'équipe de la Lanterne  
> **Salle :** La Tour de garde · combat 09.6

Certains clients ont reçu leurs points de fidélité deux fois, d'autres jamais. Et une commande annulée à la validation a quand même rapporté des points.

```text
client ada@exemple.test : commande 88 (42 €) → +42, +42
client linus@exemple.test : commande 91 (60 €) → (rien)
```

**Le monstre :** L'Hydre. Coupe un service, deux autres tombent.

**Ta mission :** un événement publié après le commit, un consommateur idempotent, et une file pour les messages en échec.
La consigne complète est dans le manoir (combat 09.6, « Publier et consommer un événement « commande créée » »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
