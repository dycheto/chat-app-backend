package com.dycheto.chatapp.dto;

public class ChatRoomRequest {
    private String name;
    private Long userId;

    public ChatRoomRequest(Long userId ){
        this.userId = userId;
    }
    public ChatRoomRequest(String name,Long userId ){
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ChatRoomRequest{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }
}
