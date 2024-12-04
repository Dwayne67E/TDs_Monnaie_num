# TD Ethereum Node  
*Monnaie numérique - Course*

## Choix des types de clients

L'architecture d'un nœud Ethereum se compose de deux types de clients :  
1. **Client d'exécution** :  
   - Traite les transactions et maintient l’état de la blockchain.  
   - Gère l'interaction avec les smart contracts.

2. **Client de consensus** :  
   - Assure la validation des blocs.  
   - Maintient le consensus au sein du réseau.

### Importance de la diversité des configurations

Pour sécuriser le réseau Ethereum, il est essentiel que les configurations des nœuds soient diversifiées. Cela permet de réduire les risques liés à la centralisation ou à une éventuelle vulnérabilité affectant une implémentation spécifique.

### Choix des clients

Afin de favoriser cette diversité, nous optons pour les implémentations suivantes :  
- **Besu** : Client d'exécution écrit en Java, conçu pour une compatibilité étendue et souvent utilisé dans des contextes d'entreprise.  
- **Teku** : Client de consensus écrit en Java, conçu pour être performant et sécurisé.  

Ces deux clients sont minoritaires dans le réseau Ethereum, ce qui contribue à renforcer la résilience et la robustesse du réseau global.
![image](https://github.com/user-attachments/assets/8dd488ae-8357-4e27-bbb2-e08566f2b2ec)

### Installation des clients
Besu et Teku sont en java et nécessite Java 11 ou une version supérieur. On vérifie donc la version sur la VM.
![image](https://github.com/user-attachments/assets/4e558b58-6fa6-4ee1-a6fe-7cfdb9110f0d)
L'installation de Teku nécessite Java 21 donc j'ai changé la version.
Il faut aussi installer gradle: 
![image](https://github.com/user-attachments/assets/21bb4cff-b5af-4d07-a71e-9453ce6b1643)

#### Client de consensus: teku
![image](https://github.com/user-attachments/assets/83d86a34-e559-49ad-aa4f-b01976c07b11)


#### Client d'éxecution: besu
