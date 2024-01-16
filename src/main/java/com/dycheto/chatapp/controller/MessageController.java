package com.dycheto.chatapp.controller;

import com.dycheto.chatapp.entity.Message;
import com.dycheto.chatapp.service.MessageService;
import com.dycheto.chatapp.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private WebSocketService webSocketService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload Message message){

        messageService.save(message);
        webSocketService.sendMessageToPublic(message);
    }

    @MessageMapping("/chat.sendPrivateMessage")
    public void sendPrivateMessage(@Payload Message message, Principal principal){
        messageService.save(message);
        webSocketService.sendPrivateMessage(principal.getName(), message);
    }

    @MessageMapping("/chat.{chatRoomId}.sendMessage")
    public void sendChatRoomMessage(@Payload Message message, @DestinationVariable String chatRoomId){
        messageService.save(message);
        webSocketService.sendMessageToChatRoom(chatRoomId, message);
    }
}
