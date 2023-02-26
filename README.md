# Introduction aux Microservices

L'application mcommerce qui est composée de 3 microservices :
'
* Microservice-produits : ce microservice gère le produit. Il propose 2 opérations simples : lister tous les produits, et récupérer un produit par son ID.

* Microservice-commandes : microservice de gestion des commandes. Il permet de passer une commande et de récupérer une commande par son ID.

* Microservice-paiements : ce microservice permet de simuler le paiement d'une commande. Une fois le paiement enregistré, il fait appel au Microservice-commandes pour mettre à jour le statut de la commande.

Dependances : 
* Java  17
* H2 database
* spring web
* spring data jpa 

Fonctionnalités :

* Microservice-produits : http://localhost:9001
    * GET : "/Produits" `List<Product> listeDesProduits()`
    * GET : "/Produits/{id}" `Optional<Product> recupererUnProduit(@PathVariable int id)`
* Microservice-commandes : http://localhost:9002
    * POST : "/commandes" `ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande)`
    * GET : "/commandes/{id}" ``Optional<Commande> recupererUneCommande(@PathVariable int id)``
* Microservice-paiements : http://localhost:9003
    * POST : "/paiement" ``ResponseEntity<Paiement>  payerUneCommande(@RequestBody Paiement paiement)``

## Créez un client

Pour créer une interface graphique, nous allons utiliser Thymeleaf comme moteur de template.
Créer un projet sur Spring Initializr, nommé client-ui:
dependances :
* spring Web
* Thymeleaf
* Bootstrap
* Feign : permet de faire communiquer les micros services 

``Feign`` est un client HTTP qui facilite grandement l'appel des API exposées par les différents microservices. Il est donc capable de créer et d'exécuter des requêtes HTTP basées sur les annotations et informations que l'on fournit. C'est un peu l'équivalent en code de Postman.

Il se présente sous forme de dépendance à ajouter au microservice.

Commençons par ajouter Feign à notre pom.xml. Dans ce cas, il ne suffira pas d'ajouter le starter Feign.

Afin d'``activer Feign`` dans ce microservice, rendez-vous à ClientUiApplication et ajoutez l'annotation ``@EnableFeignClients(package)``

L'annotation   @EnableFeignClients  demande à Feign de scanner le package "com.clientui"  pour rechercher des classes qui se déclarent clients Feign. Nous allons justement en créer une plus tard.

Fonctionalité :

* clientui : http://localhost:8080/
    * GET :"/" retourne la page HTML du nom de Accueil.html


![](/images/architecture-projet.jpg)