# 🔥 Incident · 40 tests, et aucun ne vérifie rien

> **De :** La revue de code  
> **À :** l'équipe de la Lanterne  
> **Salle :** L'Atelier des automates · combat 10.4

Les remises ont 35 % de couverture. Quelqu'un a demandé à une IA « écris des tests » : 40 tests sont arrivés, tous verts. Ils vérifient que le résultat n'est pas null. Un bug sur la remise de 10 % les traverse tous.

```text
@Test void testRemise() { assertThat(service.remise(m, 0, null)).isNotNull(); }   // × 40
```

**Le monstre :** L'Automate qui hallucine. Un agent IA qui code vite, sûr de lui, et qui invente quand il ne sait pas.

**Ta mission :** faire écrire par l'IA des tests qui vérifient la règle, et le prouver avec des mutants.
La consigne complète est dans le manoir (combat 10.4, « Compléter les tests manquants avec l'IA jusqu'au seuil »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
