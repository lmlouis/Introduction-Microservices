# Introduction aux Microservices

![](/images/architecture-projet.jpg)

## Mcommerce
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

## Client-ui

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


### Étape 1 : Récupérez la liste des produits
    * ProductBean
    * MicroserviceProduitsProxy : @FeignClient
Quand Feign fera appel à Microservice-produits  afin de récupérer la liste des produits, il lui faudra stocker chaque produit dans un objet de type   Product  afin que nous puissions les manipuler facilement plus tard

Nous allons maintenant créer une interface qui va ``regrouper les requêtes que nous souhaitons passer au Microservice-produits``. Cette interface est ce que nous appelons un ``proxy``, car elle se positionne comme une classe intermédiaire qui fait le lien avec les microservices extérieurs à appeler.

``@FeignClient``  déclare cette interface comme client Feign. Feign utilisera les informations fournies ici pour construire les requêtes HTTP appropriées afin d'appeler le Microservice-Produits.

On donne à cette annotation 2 paramètres : le premier est "name", il s'agit du nom du microservice à appeler. Il ne s'agit pas ici de n'importe quel nom, mais du nom "officiel" qui sera utilisé plus tard par des Edge Microservices comme Eureka et Ribbon.

Celui-ci est à renseigner dans application.properties du microservice à appeler grâce à ``spring.application.name``.Le deuxième paramètre est l'URL du microservice (localhost:9001)