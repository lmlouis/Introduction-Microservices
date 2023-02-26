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

* Microservice-produits : localhost:9001
    * GET : "/Produits" `List<Product> listeDesProduits()`
    * GET : "/Produits/{id}" `Optional<Product> recupererUnProduit(@PathVariable int id)`
* Microservice-commandes : localhost:9002
    * POST : "/commandes" `ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande)`
    * GET : "/commandes/{id}" ``Optional<Commande> recupererUneCommande(@PathVariable int id)``
* Microservice-paiements : localhost:9003
    * POST : "/paiement" ``ResponseEntity<Paiement>  payerUneCommande(@RequestBody Paiement paiement)``
