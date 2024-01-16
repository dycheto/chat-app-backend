package com.dycheto.chatapp.service;

import com.dycheto.chatapp.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendMessageToPublic(Message message){
        messagingTemplate.convertAndSend("/topic/public", message);
    }

    public void sendMessageToChatRoom(String chatRoomId, Message message){
        messagingTemplate.convertAndSend("/topic/chatrooms/" + chatRoomId, message);
    }

    public void sendPrivateMessage(String username, Message message){
        messagingTemplate.convertAndSendToUser(username, "/queue/messages", message);
    }
}
