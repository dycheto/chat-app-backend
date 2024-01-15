package com.dycheto.chatapp.dto;

import com.dycheto.chatapp.entity.ChatRoom;
import com.dycheto.chatapp.entity.User;

import java.util.HashSet;
import java.util.Set;

public class AuthenticationResponse {
    private String jwt;
    private Long userId;
    private String username;
    private Set<ChatRoomDTO> chatRooms;


    public AuthenticationResponse(){

    }
    public AuthenticationResponse(String username, Long userId, String jwt){
        this.username = username;
        this.userId = userId;
        this.jwt = jwt;
        this.chatRooms = new HashSet<>();
    }

    public String getJwt() {
        return jwt;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Set<ChatRoomDTO> getChatRooms() {
        return chatRooms;
    }

    public void addChatRoom(ChatRoomDTO chatRoom){
        this.chatRooms.add(chatRoom);
    }
}
