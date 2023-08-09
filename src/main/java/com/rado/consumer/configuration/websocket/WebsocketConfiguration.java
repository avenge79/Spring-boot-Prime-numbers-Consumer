package com.rado.consumer.configuration.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*");
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS().setSupressCors(true);
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // Uncomment for debugging
//        registration.interceptors(new ChannelInterceptor() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//                System.out.println("StompCommand: " + accessor.getCommand());
//                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//                    System.out.println("Connected SessionId: " + message.getHeaders().get(SimpMessageHeaderAccessor.SESSION_ID_HEADER));
//
//                } else if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
//                    System.out.println("Subscribe ");
//                } else if (StompCommand.SEND.equals(accessor.getCommand())) {
////                    System.out.println(message);
//                    System.out.println("Send message ");
//                } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
//                    System.out.println("Exit ");
//                }
//                return message;
//            }
//        });
    }
}
