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

Afin de favoriser cette diversité, nous devrions opter pour les implémentations suivantes :  
- **Besu** : Client d'exécution écrit en Java, conçu pour une compatibilité étendue et souvent utilisé dans des contextes d'entreprise.  
- **Teku** : Client de consensus écrit en Java, conçu pour être performant et sécurisé.  

Ces deux clients sont minoritaires dans le réseau Ethereum, ce qui contribue à renforcer la résilience et la robustesse du réseau global.
![image](https://github.com/user-attachments/assets/8dd488ae-8357-4e27-bbb2-e08566f2b2ec)
Mais par facilité j'ai choisi geth et prysm. 

#### Create a non root user
To reduce the risk of accidental changes, it's safer to avoid using the root user. Instead, we create a new user with sudo privileges.
![image](https://github.com/user-attachments/assets/98ad9b0a-58a3-405b-99f5-c3013c5cd2e3)

#### Verify System time 
Next, we check that the time and timezone are accurate and that the system clock is synchronized. 
This is important for logging and network operations.
![image](https://github.com/user-attachments/assets/9e0c5140-1239-472a-889b-e02aba97d561)

#### Configure the firewall 
To ensure the Ethereum node can communicate with the network while staying secure, we need to configure the firewall to allow specific ports. In this step, we'll open the ports needed by Geth and Prysm, and enable the firewall on our server:
![image](https://github.com/user-attachments/assets/7557277b-0a5a-4c9c-a509-798cf2b3902b)

The execution client (Geth) uses port 30303 for its peer-to-peer connections. Ports 12000 and 13000 are used for Prysm's beacon chain discovery and communication.

Finally, confirm the status of your firewall:
![image](https://github.com/user-attachments/assets/611cf0d2-d3cb-4c2b-9492-8a05132c5ed2)

#### Generate authentification secret  ---- A partir de là faut corriger

For Geth (the execution client) and Prysm (the consensus client) to communicate securely, they need a shared secret, known as a JSON Web Token (JWT). This token ensures that only authorized clients can interact with each other.

Before generating the secret, it's a good practice to create dedicated users for each client. This minimizes the risk of one client affecting the other and isolates their files and processes.

Start by creating the users and assigning them to a common group:
![image](https://github.com/user-attachments/assets/3dc696d4-cb9d-44c7-992d-b7d9c902e1ab)

Next, create a directory to store the JWT secret, set the necessary permissions, and generate the secret:

![image](https://github.com/user-attachments/assets/bd749c03-86f5-4b77-9e20-b206a9eb9c60)

Then set permissions on the secret file so that only the root user and the clients' users have access to it:

![image](https://github.com/user-attachments/assets/abe3d49b-df9d-4242-95d5-4c1d2631ac1d)

#### Create data directories 

To keep the data for each client organized, you need to create dedicated directories where Geth and Prysm will store their data.

Run the following commands to create the data directories for each client:
![image](https://github.com/user-attachments/assets/25bb8eaa-4167-4214-a1cb-54f917e87d1b)

#### Install and configure execution client (Geth)

In this step, you'll install Geth, the execution client, and configure it to run as a service on your server. This ensures that Geth starts automatically when you boot your server and will continue to run as long as the server is on.

Add the Ethereum PPA and Install Geth:

Start by adding the Ethereum PPA (Personal Package Archive) to your system's repositories and installing Geth:
![image](https://github.com/user-attachments/assets/a5a7c88e-251c-4491-bfbc-ce2932e31643)


Create a Systemd Service for Geth:

To manage Geth as a background service, you'll create a systemd service file. This file defines how Geth should be started, stopped, and restarted.

Open the service file in a text editor using: 
*sudo nano /etc/systemd/system/geth.service*

![image](https://github.com/user-attachments/assets/a608327f-42dd-4f68-bbbb-eef8620eabd7)

This configuration specifies that Geth should run as the geth user, use the data directory you created earlier and authenticate its RPC (Remote Procedure Call) communications using the JWT secret.

Start and Enable the Geth Service: 

After saving the service file, reload the systemd daemon to apply the changes, start the Geth service, and enable it to start on boot:

![image](https://github.com/user-attachments/assets/d6c45020-e6fe-4e8a-966e-5c91826c5be9)

Check the Status of the Geth Service:

Verify that Geth is active running correctly by checking its status:

![image](https://github.com/user-attachments/assets/b2fd8f82-db89-41e9-909b-b7ef8adb07c0)

To view the logs, run the command:

![image](https://github.com/user-attachments/assets/9277de04-39df-45f8-8cad-c6ff64d68780)
Watch the log: Post-merge network, but no beacon client seen. Please launch one to follow the chain!

#### Configure consensus client (Prysm)

Now that Geth is up and running, the next step is to set up the Prysm Beacon Chain client, which acts as the consensus client.

Download and Prepare the Prysm Script:
First, create a directory for the Prysm script, download it and make it executable:
