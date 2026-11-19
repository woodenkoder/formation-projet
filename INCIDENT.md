# 🔥 Incident · NullPointerException dans un test « tout simple »

> **De :** Sam, développeur arrivé lundi  
> **À :** l'équipe de la Lanterne  
> **Salle :** La Salle des machines · combat 03.2

J'ai voulu tester CommandeService avec new, comme on m'a appris. Ses dépendances sont restées à null. Pour tester une méthode de 10 lignes, il faut démarrer toute l'application : 9 secondes par test.

```text
java.lang.NullPointerException: Cannot invoke "CommandeRepository.enregistrer(...)"
    because "this.repository" is null
    at CommandeService.creer(CommandeService.java:31)
```

**Le monstre :** Le Golem du controller. Un controller qui fait tout, tout seul, et que plus personne ne peut tester.

**Ta mission :** passer à l'injection par constructeur, et des tests sans Spring.
La consigne complète est dans le manoir (combat 03.2, « Passer à l'injection par constructeur »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
