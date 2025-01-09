package com.example.event_indexer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import com.example.event_indexer.websocket.EventWebSocketController;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final EventWebSocketController eventWebSocketController;

    public WebSocketConfig(EventWebSocketController eventWebSocketController) {
    	System.out.println("dans WebSocketConfig");
        this.eventWebSocketController = eventWebSocketController;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    	System.out.println("dans registerWebSocketHandlers");
        registry.addHandler(eventWebSocketController, "/ws")  // Définir l'URL WebSocket
                .setAllowedOrigins("*");  // Autoriser tous les origines (ajustez selon vos besoins)
    }
}
