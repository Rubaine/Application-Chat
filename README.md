# Application de Chat TCP

## Description

Ce projet est une application de chat centralisée qui utilise les sockets TCP pour la communication entre les clients à travers un serveur central. L'application permet aux utilisateurs de se connecter à un salon de discussion, d'envoyer et de recevoir des messages en temps réel.

## Fonctionnalités

- **Connexion au serveur** : Les utilisateurs peuvent se connecter au serveur en utilisant un pseudo unique.
- **Salon de discussion partagé** : Une fois connectés, les utilisateurs accèdent à un salon de discussion où ils peuvent envoyer et recevoir des messages avec d'autres utilisateurs.
- **Gestion des utilisateurs connectés** : Le serveur gère une liste des clients connectés et s'occupe de la redirection des messages entre eux.
- **Interface graphique utilisateur** : L'application offre une interface graphique permettant la saisie du pseudo, la visualisation des messages du salon, et l'envoi de messages.

## Technologies utilisées

- JavaFX pour l'interface graphique.
- Java Socket pour la communication réseau.

## Architecture

Le projet est divisé en plusieurs composants principaux :

- **Serveur** : Gère les connexions entrantes, instancie un thread par client pour écouter les messages et les redistribue aux autres clients.
- **Client** : Permet à l'utilisateur de se connecter au serveur, d'envoyer des messages et de recevoir des messages des autres utilisateurs.
- **Interface Utilisateur** : Fournit une interface graphique pour interagir avec l'application.

## Comment démarrer

### Prérequis

- Java 11 ou supérieur.
- Maven pour la gestion des dépendances.

### Lancer le serveur

1. Lancer la classe Server.

### Lancer l'application cliente

1. Lancer la classe ChatApplication.

## Contribution

Les contributions sont les bienvenues ! Si vous souhaitez contribuer, veuillez forker le dépôt, créer une branche pour vos modifications, et soumettre une pull request.

## Licence

[MIT](LICENSE.md)
