# 🔥 Incident · Un client lit les commandes des autres

> **De :** Un client honnête  
> **À :** l'équipe de la Lanterne  
> **Salle :** Les Oubliettes · combat 07.3

« Bonjour, en changeant client=ada par client=linus dans l'adresse, je vois la commande d'un autre client, avec son adresse. Je préfère vous prévenir. »

```text
GET /api/commandes/2?client=linus   (connecté en tant qu'ada)
→ 200 OK  {"id": 2, "client": "linus", ...}
```

**Le monstre :** Le Voleur de clés. Il entre par la faille que tu n'as pas vue, et repart avec tes données.

**Ta mission :** l'identité vient du jeton, jamais d'un paramètre ; chacun ne voit que ce qui est à lui.
La consigne complète est dans le manoir (combat 07.3, « Protéger les endpoints admin avec Spring Security »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
