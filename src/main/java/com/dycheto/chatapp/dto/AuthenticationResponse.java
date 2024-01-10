package com.dycheto.chatapp.dto;

import com.dycheto.chatapp.entity.User;

public class AuthenticationResponse {
    private String jwt;
    private Long userId;
    private String username;


    public AuthenticationResponse(){

    }
    public AuthenticationResponse(String username, Long userId, String jwt){
        this.username = username;
        this.userId = userId;
        this.jwt = jwt;
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

}
