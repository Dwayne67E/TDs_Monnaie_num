package com.example.event_indexer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Log;

import com.example.event_indexer.entity.Event;
import com.example.event_indexer.repository.EventRepository;
import com.example.event_indexer.websocket.EventWebSocketController;

import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Service
public class EventIndexerService {

    private static final String USER_OPERATION_EVENT_TOPIC = "0x49628fd1471006c1482da88028e9ce4dbb080b815c9b0344d39e5a8e6ec1419f";
    private static final String ENTRY_POINT_ADDRESS = "0x0000000071727de22e5e9d8baf0edac6f37da032";

    @Autowired
    private EventRepository eventRepository;

    private final Web3j web3j;

    @Autowired
    private EventWebSocketController webSocketController;

    @Autowired
    public EventIndexerService(Web3j web3j) {
        this.web3j = web3j;
    }

    @PostConstruct
    public void startListening() {
        System.out.println("Vérification de la connexion à Ethereum...");
        try {
            // Ajouter un événement test à la base de données

            BigInteger blockNumber = web3j.ethBlockNumber().send().getBlockNumber();
            System.out.println("Connecté à Ethereum ! Block actuel : " + blockNumber.toString());

            // Créer un filtre pour écouter les événements du topic spécifique
            EthFilter filter = new EthFilter(
                    DefaultBlockParameterName.LATEST,
                    DefaultBlockParameterName.LATEST,
                    ENTRY_POINT_ADDRESS
            );
            filter.addSingleTopic(USER_OPERATION_EVENT_TOPIC);

            // Lancer l'écoute des événements avec Web3j
            web3j.ethLogFlowable(filter).subscribe(this::processEvent, this::handleError);
            System.out.println("L'écoute des événements est en cours...");
        } catch (Exception e) {
            System.err.println("Erreur lors de la connexion : " + e.getMessage());
        }
    }


    // Méthode pour traiter l'événement capturé
    private void processEvent(Log log) {
        System.out.println("\nNouvel événement détecté :");
        List<String> topics = log.getTopics();
        String eventTopic = topics.get(0);

        if (eventTopic.equals(USER_OPERATION_EVENT_TOPIC)) {
            processUserOperationEvent(log);
        }
    }

    // Traitement de l'événement `UserOperationEvent`
    private void processUserOperationEvent(Log log) {
        String transactionHash = log.getTransactionHash();

        // Vérifie si la transaction a déjà été traitée
        if (eventRepository.existsById(transactionHash)) {
            System.out.println("Transaction déjà traitée : " + transactionHash);
            return;
        }
        Event transaction = new Event();
        
        transaction.setUserOpHash(log.getTransactionHash());// set TransactionHash
        transaction.setBlockNumber(log.getBlockNumber()); //set blockNumber
        
        EthTransaction ethTransaction;
		
		try {
			EthGetTransactionReceipt receiptResponse = web3j.ethGetTransactionReceipt(transactionHash).send();
			transaction.setActualGasUsed(receiptResponse.getTransactionReceipt().get().getGasUsed());// set gasUsed
			transaction.setPaymaster(receiptResponse.getTransactionReceipt().get().getTo()); //set Paymaster
			transaction.setSuccess(receiptResponse.getTransactionReceipt().get().getStatus().equals("0x1"));
			try {
				ethTransaction = web3j.ethGetTransactionByHash(transactionHash).send();
				transaction.setNonce(ethTransaction.getTransaction().get().getNonce());// set nonce
				transaction.setSender(ethTransaction.getTransaction().get().getFrom());// set sender
				BigInteger gasPrice = ethTransaction.getTransaction().get().getGasPrice();
				transaction.setActualGasCost(gasPrice.multiply(transaction.getActualGasUsed())); //set gasCost
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        eventRepository.save(transaction);
        System.out.println("Nouvelle transaction sauvegardée : " + transactionHash);

        // Envoi de l'événement via WebSocket
        webSocketController.notifyNewTransaction(transaction);
    }

    private void handleError(Throwable throwable) {
        System.err.println("Erreur lors de l'écoute des événements : " + throwable.getMessage());
    }
}
