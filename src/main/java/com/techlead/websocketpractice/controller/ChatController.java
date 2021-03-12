package com.techlead.websocketpractice.controller;

import com.techlead.websocketpractice.model.ChatMessage;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;



//Controller의 메소드는 한 Client에게서 message를 수신한 다음 다른 Client에게 Broadcast합니다.
@Controller
public class ChatController {
    // "/app"로 시작하는 대상이 있는 클라이언트에서 보낸 모든 메시지는
    // @MessageMapping 어노테이션이 달린 메서드로 라우팅 됩니다.
    // 예를 들어 "/app/chat.sendMessage"인 메시지는 sendMessage()로 라우팅 되며
    // "/app/chat.addUser"인 메시지는 addUser()로 라우팅된다.
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        System.out.println("sendMessage method!!!***");
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        System.out.println("addUser method!!!***");
        return chatMessage;
    }
}
