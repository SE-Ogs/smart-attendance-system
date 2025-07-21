package com.attendance.attendance.session.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // These are prefixes for messages FROM the server TO the client
        config.enableSimpleBroker("/topic", "/queue");
        // This is a prefix for messages FROM the client TO the server
        config.setApplicationDestinationPrefixes("/app");
        // This enables user-specific destinations (e.g., /user/{userId}/queue/...)
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // This is the HTTP endpoint that clients will connect to for the WebSocket handshake
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*") // Allow all origins for CORS; adjust as needed for security
                .withSockJS();                
    }
}