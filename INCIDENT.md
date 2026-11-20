# 🔥 Incident · La CI est rouge à cause du style

> **De :** La CI
> **À :** l'équipe de la Lanterne
> **Salle :** La Bibliothèque maudite · combat 02.2

Checkstyle et Spotless viennent d'être activés dans la CI de la Lanterne. Résultat : rouge. Import étoile, accolades oubliées, exception avalée en silence, noms incohérents. La tentation : désactiver la règle. Ici, on corrige le code.

```text
[WARN] Stock.java:3:17: Using the '.*' form of import should be avoided - java.util.*. [AvoidStarImport]
[WARN] Stock.java:20:9: 'if' construct must use '{}'s. [NeedBraces]
[WARN] Stock.java:28:51: Empty catch block. [EmptyCatchBlock]
```

**Le monstre :** Le Spaghetti. Du code où tout est emmêlé, que personne n'ose toucher.

**Ta mission :** remettre le code d'aplomb pour que le format et les règles de l'équipe passent, sans toucher aux règles.
La consigne complète est dans le manoir (combat 02.2, « Corriger les alertes du linter »).

**Lancer le combat :** Pousse sur la branche `exercice/linter` : le workflow « Manoir » lance le combat. Le résumé du run te dit combien de points de vie il reste au monstre.
