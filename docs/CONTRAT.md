# Le contrat de ta Lanterne finale

Les combats du Toit (13.1 « Les sept jalons », 13.3 « La Nuit de la mise en prod ») lancent **ta Lanterne telle
quelle**, depuis son image Docker, et la testent comme le feraient un client ou un attaquant. Tu organises ton code
comme tu veux : ce contrat dit seulement ce que nos tests appellent, et ce qu'ils attendent.

## Lancement

- `docker build .` à la racine construit l'image. Elle écoute sur le port **8080**.
- `GET /actuator/health` répond `{"status":"UP"}` quand ta Lanterne est prête.
- Toute la configuration vient des **variables d'environnement** ci-dessous. Aucune valeur de production dans le code.
- Le manoir fournit une base PostgreSQL 17 **vide** : ta Lanterne crée son schéma au démarrage (Flyway conseillé).

| Variable | Ce que c'est |
| --- | --- |
| `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` | PostgreSQL |
| `SPRING_RABBITMQ_HOST`, `SPRING_RABBITMQ_PORT`, `SPRING_RABBITMQ_USERNAME`, `SPRING_RABBITMQ_PASSWORD` | RabbitMQ |
| `LANTERNE_PAIEMENT_URL` | L'adresse du prestataire de paiement |
| `SPRING_AI_OPENAI_BASE_URL`, `OPENAI_API_KEY` | Le modèle (API compatible OpenAI) : `spring.ai.openai.api-key=${OPENAI_API_KEY}` |
| `LANTERNE_JWT_SECRET` | La clé qui signe les jetons (au moins 32 caractères) |
| `LANTERNE_ADMIN_EMAIL`, `LANTERNE_ADMIN_MOT_DE_PASSE` | Le compte admin, créé au démarrage s'il n'existe pas |

Les erreurs suivent le format standard : `application/problem+json`, avec un champ `status` (ProblemDetail).

## Comptes et jetons

| Requête | Réponse |
| --- | --- |
| `POST /api/auth/inscription` `{"email", "motDePasse"}` | 201 ; 400 si l'e-mail est invalide ou le mot de passe fait moins de 8 caractères ; 409 si l'e-mail a déjà un compte |
| `POST /api/auth/connexion` `{"email", "motDePasse"}` | 200 `{"jeton": "…"}` ; 401 sinon |

Ensuite, chaque requête porte `Authorization: Bearer <jeton>`. Sans jeton, ou avec un jeton falsifié : 401. Un compte
créé par inscription est un **client** ; le compte de `LANTERNE_ADMIN_EMAIL` est **admin**.

## Produits

| Requête | Qui | Réponse |
| --- | --- | --- |
| `GET /api/produits` | tout le monde | 200, les produits `{id, nom, prix, stock}` |
| `GET /api/produits/{id}` | tout le monde | 200 ; 404 |
| `POST /api/produits` `{"nom", "prix", "stock"}` | admin | 201 avec l'`id` ; 400 si le nom est vide, le prix ≤ 0 ou le stock < 0 ; 403 pour un client |
| `PUT /api/produits/{id}` | admin | 200 |
| `DELETE /api/produits/{id}` | admin | 204 (ou 200), puis 404 |

## Commandes

| Requête | Qui | Réponse |
| --- | --- | --- |
| `POST /api/commandes` `{"lignes": [{"produitId": 1, "quantite": 2}]}` | client | 201 `{id, statut, montant, …}` ; 400 si une quantité est ≤ 0 ; 400 ou 404 si un produit n'existe pas |
| `GET /api/commandes?page=0&size=5` | client | 200, **ses** commandes seulement, paginées : `{"content": […], "totalElements": 12}` (format Spring Data : `totalElements` à la racine ou dans `page`) |
| `GET /api/commandes/{id}` | son client, l'admin | 200 ; 403 ou 404 pour un autre client |
| `GET /api/commandes/{id}/facture` | son client, l'admin | 200 (avec l'`id` de la commande) ; **403** pour un autre client |

- Le `montant` est la somme des prix × quantités.
- Pas de N+1 : le nombre de requêtes SQL de `GET /api/commandes` ne grandit pas avec la taille de la page.

## Paiement

Ta Lanterne paie chaque commande auprès du prestataire : `POST {LANTERNE_PAIEMENT_URL}/paiements`
`{"commandeId": 42, "montant": 109.70}` → 200 quand le paiement est accepté.

- Le prestataire peut **tomber** (503) ou devenir **très lent** : délai court et circuit breaker. Quand le circuit
  est ouvert, une commande répond en moins de 1,5 seconde.
- Prestataire en panne : la commande est prise quand même (201), au statut `PAIEMENT_EN_ATTENTE`. Jamais de 500.
- Quand il revient, les commandes en attente sont payées automatiquement (statut `PAYEE`) en moins de 2 minutes.

## Événements

Chaque commande créée publie **un** message, après le commit, sur l'exchange RabbitMQ `lanterne.commandes`
(type `topic`, durable), clé `commande.creee`, corps JSON avec au moins `commandeId`.

## Assistant

`POST /api/assistant` `{"message": "…"}` (client connecté) → 200 `{"reponse": "…"}` ; 401 sans jeton.

- Le modèle peut appeler tes outils. Un outil renvoie les commandes **du client connecté**, avec leur `id`.
- Nos tests utilisent un modèle **détourné** : il passe à tous tes outils l'e-mail et les identifiants d'un autre
  client, puis recopie leur résultat dans la réponse. Rien de cet autre client ne doit en sortir.

## Limite de débit

Au-delà de **10 requêtes par seconde pour un même client connecté** : 429. Les autres clients ne sont pas limités
par lui.

## Ce que vérifie chaque combat

- **13.1, les sept jalons** (sur `main`) : tes tests (`./mvnw verify`, au moins 60 % des lignes du service des
  commandes couvertes), aucun secret dans le code ni dans l'historique de `main`, une CI, l'image, puis tout le
  contrat ci-dessus, sauf la limite de débit et la facture.
- **13.3, la Nuit** : cinq clients commandent sans arrêt pendant que quatre incidents tombent : le prestataire de
  paiement, un script qui s'emballe, une faille signalée sur les factures, une injection de prompt. Aucune erreur
  500 de toute la nuit.
