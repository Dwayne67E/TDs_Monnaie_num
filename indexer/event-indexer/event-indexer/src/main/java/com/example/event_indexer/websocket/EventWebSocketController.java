package com.example.event_indexer.websocket;

import com.example.event_indexer.entity.Event;
import com.example.event_indexer.service.EventIndexerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigInteger;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class EventWebSocketController extends TextWebSocketHandler {

    private WebSocketSession session;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Pour convertir les objets en JSON

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.session = session;
        System.out.println("WebSocket connecté");
        createTest();
    }
    
    public void createTest() {
    	Event testEvent = new Event();
        testEvent.setUserOpHash("testHash");
        testEvent.setBlockNumber(BigInteger.valueOf(0000));
        testEvent.setSender("testSender");
        testEvent.setPaymaster("testPayMaster");
        testEvent.setNonce(BigInteger.valueOf(0000));
        testEvent.setSuccess(false);
        testEvent.setActualGasUsed(BigInteger.valueOf(0000));
        testEvent.setActualGasCost(BigInteger.valueOf(0000));
        notifyNewTransaction(testEvent);
    }

    // Méthode pour envoyer un événement au client sous forme JSON
    public void notifyNewTransaction(Event event) {
    	System.out.println("notifyNewTransaction called");
        try {
            if (session != null && session.isOpen()) {
                // Convertir l'objet Event en JSON
                String eventJson = objectMapper.writeValueAsString(event);
                // Envoyer le JSON via WebSocket
                session.sendMessage(new TextMessage(eventJson));
                System.out.println("Événement envoyé au client : " + eventJson);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'événement : " + e.getMessage());
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Message reçu: " + message.getPayload());
    }
    
}
