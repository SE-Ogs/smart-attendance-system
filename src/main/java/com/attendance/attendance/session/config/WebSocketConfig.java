package com.attendance.attendance.session.config; // Choose a suitable package for configuration, e.g., com.attendance.attendance.config

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Enables WebSocket message handling
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Defines a prefix for messages that are broadcast to clients (topics)
        config.enableSimpleBroker("/topic", "/user"); // Added /user for private messages
        // Defines a prefix for messages that are sent from clients to the server
        config.setApplicationDestinationPrefixes("/app");
        // Configures the destination for user-specific messages (e.g., /user/{userId}/queue/notifications)
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Registers a STOMP endpoint where clients can connect to the WebSocket server
        registry.addEndpoint("/ws").withSockJS(); // Uses SockJS for fallback options
    }
}