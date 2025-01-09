# Event Indexer Service

## Description

Event Indexer Service est une application Spring Boot qui écoute les événements Ethereum en temps réel, les stocke dans une base de données, et les envoie aux clients via WebSocket. Elle est conçue pour interagir avec une blockchain Ethereum en capturant les logs pertinents liés à un `topic` spécifique et les rendant disponibles pour une interface utilisateur.

---

## Fonctionnalités

1. **Capture d'événements en temps réel :** 
   - Écoute des événements Ethereum pour un `topic` spécifique en utilisant Web3j.
   - Décodage et traitement des données des logs capturés.

2. **Stockage des événements :**
   - Sauvegarde des événements dans une base de données H2 en utilisant JPA.

3. **Notification en temps réel :**
   - Envoi des événements capturés aux clients via un canal WebSocket.

4. **Simulation d'événements :**
   - Possibilité de créer un événement de test pour valider les flux.

---

## Technologies utilisées

- **Langage :** Java
- **Framework :** Spring Boot
- **Blockchain :** Web3j
- **Base de données :** H2
- **WebSocket :** STOMP et SockJS pour les notifications en temps réel
- **Frontend :** HTML, JavaScript avec STOMP.js pour la gestion des WebSockets.

---

## Prérequis

- JDK 17 ou plus récent
- Maven 3.6+ ou Gradle
- Un réseau Ethereum accessible pour écouter les événements (ex : Infura)

---

## Structure du projet

- **Controller :** 
  - `EventWebSocketController` : Gère la connexion WebSocket et envoie des messages JSON aux clients connectés.
  
- **Service :** 
  - `EventIndexerService` : Contient la logique pour écouter les événements Ethereum et gérer leur traitement.
  
- **Entity :**
  - `Event` : Représente un événement capturé et est stocké dans la base de données.

- **Repository :**
  - `EventRepository` : Interface JPA pour interagir avec la base de données.

- **Configuration :**
  - `WebSocketConfig` : Configure l'endpoint WebSocket pour `/ws`.

---

## Comment exécuter le projet ?

1. Clonez le projet :
   ```bash
   git clone https://github.com/votre-repo/event-indexer-service.git
   cd event-indexer-service
2. Configurez l'accès au réseau Ethereum dans `application.properties` :
   ```properties
   web3j.client-address=https://mainnet.infura.io/v3/votre-infura-api-key
3. Compilez et démarrez le projet :
   ```bash
   mvn spring-boot:run
4. Ouvrez un navigateur et accédez à l'interface utilisateur :
   ```bash
   [mvn spring-boot:run](http://localhost:8080)

## Fonctionnement

### Écoute des événements Ethereum
Le service utilise un filtre (`EthFilter`) pour écouter les logs Ethereum liés à une adresse spécifique et un `topic`. Les données sont ensuite décodées et stockées dans la base de données.

### Notification des événements
Les événements capturés sont envoyés en temps réel aux clients via un WebSocket configuré sur `/ws`.

---

## Points importants

- **Gestion des erreurs :**
  - Les événements trop longs sont tronqués avant d'être stockés dans la base de données.
  
- **Test des flux :**
  - Vous pouvez utiliser la méthode `createTestEvent` pour simuler un événement et vérifier son affichage.

- **Interface utilisateur :**
  - L'interface affiche les événements capturés en temps réel et met à jour la liste sans rechargement de page.

---

## Contribuer

Les contributions sont les bienvenues ! Créez une *issue* ou soumettez une *pull request* avec des améliorations ou des corrections.  
Surtout n'hésitez pas à piquer le projet pour le présenter à l'école. Vive la triche !

---

## Licence

Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.

