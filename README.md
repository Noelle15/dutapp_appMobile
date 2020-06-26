# dutapp_appMobile
## Description
Projet DUT Informatique - Application Mobile EBOOK

## Concept
* Lister les livres que l'on a lu
* Mettre en favoris des livres
* Découvrir des livres en fonction de ses goûts
* Avoir le temps qu'on a passé à lire
* Noter les livres, commenter, échanger
## Ce qui a été réalisé 
* se connecter avec les identifiants : bobibette@gmail.com et mot de passe : bobibette (unique utilisateur de la base firebase)
* récupérer la collection livre 
* récupéter l'utilisateur et la sous collecter librairie de la collection utilisateur 
* éléments visuels du profil, livres et recherche
* Afficher les livres de la base de données

## Architecture Firebase 
###### Collection Book (id Random)
* author
* title
* description
* type
###### Collection User (id -> email) 
* login
* Sous collection Library (comment, grade, bookmark, idBook)


## Pour faire fonctionner les imports de la base de données
Afin de faire fonctionner l'ensemble, la première étape est de se connecter avec les identifiants "bobibette@gmail.com" de mot de passe bobibette. 
Ensuite, une fois connecté, il faut fermer l'application puis la réouvrir. Vous avez maintenant une application fonctionnelle où les imports fonctionnent.

## Réalisés par
* Gaëlle
* Noëlle
* Tristan 
