package com.dycheto.chatapp.dto;

import java.util.HashSet;
import java.util.Set;

public class UserResponse {

    private Long userId;
    private String username;
    private Set<ChatRoomDTO> chatRooms;


    public UserResponse(){
    }
    public UserResponse(Long userId, String username){
        this.username = username;
        this.userId = userId;
        this.chatRooms = new HashSet<>();
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
