package com.dycheto.chatapp.dto;

public class UserRequest {
    private String name;
    private Long userId;

    public UserRequest(Long userId ){
        this.userId = userId;
    }
    public UserRequest(String name, Long userId ){
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
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }
}
