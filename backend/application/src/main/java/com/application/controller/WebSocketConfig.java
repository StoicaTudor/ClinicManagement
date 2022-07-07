package com.application.controller;

import com.application.controller.webSocketCommunication.UrlMappings;
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
        config.enableSimpleBroker(UrlMappings.MESSAGE_BROKER);
        config.setApplicationDestinationPrefixes(UrlMappings.APP);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(UrlMappings.SECRETARY_STOMP_ENDPOINT);
        registry.addEndpoint(UrlMappings.SECRETARY_STOMP_ENDPOINT).withSockJS();
    }
}
