# 🔥 Incident · L'appli mobile ne sait jamais si ça a marché

> **De :** L'équipe mobile  
> **À :** l'équipe de la Lanterne  
> **Salle :** Le Standard · combat 05.1

Supprimer un produit qui n'existe pas renvoie 200. Le lire aussi, avec un corps vide. Et vos URL ont des verbes dedans : notre client HTTP générique ne sait pas les appeler.

```text
POST /api/produits/delete/9999 → 200 OK
GET  /api/produits/get/9999    → 200 OK  (corps vide)
```

**Le monstre :** La Banshee 500. Elle hurle des erreurs serveur à chaque appel mal pensé.

**Ta mission :** un CRUD qui parle HTTP : les bons verbes, les bons codes.
La consigne complète est dans le manoir (combat 05.1, « Un CRUD qui respecte les conventions »).

Pousse sur cette branche : le workflow « Manoir » lance le combat, et son résumé te dit combien de points de vie
il reste au monstre.
