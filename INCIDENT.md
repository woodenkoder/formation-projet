# 🔥 Incident · Débité, jamais crédité

> **De :** Le service client  
> **À :** l'équipe de la Lanterne  
> **Salle :** La Salle des machines · combat 03.4

Linus a viré 1 500 € de sa cagnotte vers celle d'Ada. Linus a bien été débité. Ada n'a rien reçu. L'argent a disparu entre deux lignes de code, et Linus parle d'appeler sa banque.

```text
SELECT compte_id, montant FROM mouvement WHERE date > now() - interval '1 hour';
 compte_id | montant
         7 | -1500.00
(1 ligne)
```

**Le monstre :** Le Golem du controller. Un controller qui fait tout, tout seul, et que plus personne ne peut tester.

**Ta mission :** rendre le virement atomique : en entier, ou pas du tout.
La consigne complète est dans le manoir (combat 03.4, « Corriger un virement enregistré à moitié »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
