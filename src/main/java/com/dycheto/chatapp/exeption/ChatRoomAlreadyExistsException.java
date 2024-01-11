package com.dycheto.chatapp.exeption;

public class ChatRoomAlreadyExistsException extends RuntimeException{

    public ChatRoomAlreadyExistsException(String message){
        super(message);

    }
}
