package com.dycheto.chatapp.controller;

import com.dycheto.chatapp.entity.Message;
import com.dycheto.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(Message message){

        messageService.save(message);

        return message;
    }

    @MessageMapping("/chat.sendPrivateMessage")
    @SendToUser("/queue/messages")
    public Message sendPrivateMessage(Message message, Principal principal){

        return message;
    }
}
