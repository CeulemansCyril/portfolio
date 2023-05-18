Ceulemans
Cyril
Projet developpement web

lien git : git@gitlab.com:Shirisu/ProjectFinalWeb.git


Installation minimum :

    - PHP 8.2 +
    - MySQL 8.0.31
    - GIT Bash

Installation du project web :

    1) Lancer votre serveur MySQL
    2) Completer le fichier config.php
    3) Lancer Git et rendez-vous dans le fichier migration du projet
    4) Utiliser la commande "php migration.php --all" pour pouvoir faire l'installation de la base de données
    5) Une fois les migrations utiliser un utilisateur admin par default sera créé
        - Nom d'utilisateur : toto
        - Mot de passe : toto

Bug possible :

    1) migration.php --update

future implémentation :

    1) liers la table pays avec la création du compte pour proposer plusieurs pays