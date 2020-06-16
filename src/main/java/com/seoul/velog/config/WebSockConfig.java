package com.seoul.velog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration  // 해당 class는 spring환경 설정과 관련된 파일임을 선언
@EnableWebSocketMessageBroker   //stomp를 사용하기 위해 선언
public class WebSockConfig implements WebSocketMessageBrokerConfigurer {

    //--- prefix 설정
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.enableSimpleBroker("/sub");  // 구독
        config.setApplicationDestinationPrefixes("/pub");   // 요청
    }

    //--- stomp websocket의 연결 endpoint설정
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/ws-stomp").setAllowedOrigins("*")
                .withSockJS();
    }
}
