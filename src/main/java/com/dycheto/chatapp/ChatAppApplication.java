package com.dycheto.chatapp;

import com.dycheto.chatapp.entity.ChatRoom;
import com.dycheto.chatapp.entity.Message;
import com.dycheto.chatapp.entity.User;
import com.dycheto.chatapp.service.ChatRoomService;
import com.dycheto.chatapp.service.MessageService;
import com.dycheto.chatapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;


@SpringBootApplication
public class ChatAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatAppApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(UserService userService, ChatRoomService chatRoomService, MessageService messageService) {
        return runner -> {
            sendMessageToChatRoom(userService, chatRoomService, messageService);
        };
    }

    private void sendMessageToChatRoom(UserService userService, ChatRoomService chatRoomService, MessageService messageService) throws Exception{
        userService.registerNewUser(new User("Shabby", "123"));
        Optional<User> shabby = userService.findByUsername("Shabby");
        long id = 2;
        ChatRoom chatRoom = chatRoomService.findChatRoomById(id);

        Message newMessage = new Message(shabby.get(),chatRoom, "Test test 123. Kwo stava maniaci?");
        messageService.save(newMessage);
    }

}
