# 🔥 Incident · Notre clé de paiement est dans le code

> **De :** La sécurité  
> **À :** l'équipe de la Lanterne  
> **Salle :** Les Oubliettes · combat 07.4

La clé du service de paiement est écrite en dur dans PaiementClient.java. Et la configuration CORS accepte n'importe quel site, cookies compris : un site piégé peut appeler l'API au nom d'un client connecté.

```text
private static final String CLE_API = "pay-live-…";
.allowedOriginPatterns("*").allowCredentials(true)
```

**Le monstre :** Le Voleur de clés. Il entre par la faille que tu n'as pas vue, et repart avec tes données.

**Ta mission :** les secrets dans l'environnement, CORS limité, et un scanner de secrets dans la CI.
La consigne complète est dans le manoir (combat 07.4, « Sortir les secrets du code et régler CORS »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
