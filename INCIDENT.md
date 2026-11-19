# 🔥 Incident · Le transporteur tombe, tout tombe

> **De :** L'astreinte  
> **À :** l'équipe de la Lanterne  
> **Salle :** La Tour de garde · combat 09.2

Le transporteur a eu une panne de 20 minutes. Chaque validation de commande attendait sa réponse 30 secondes. Tous les threads de la Lanterne étaient bloqués : plus personne ne pouvait commander, même sans livraison.

```text
WARN  Tomcat: all 200 threads busy
GET /delai?codePostal=75001  … 30 012 ms  (timeout)
```

**Le monstre :** L'Hydre. Coupe un service, deux autres tombent.

**Ta mission :** un timeout, un circuit breaker et un repli : une panne chez eux ne devient pas une panne chez nous.
La consigne complète est dans le manoir (combat 09.2, « Circuit breaker et repli sur un appel externe »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
