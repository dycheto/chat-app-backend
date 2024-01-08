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

//			saveUser(userService);
//			getUser(userService);
//			deleteUserById(userService);
//			saveChatRoom(chatRoomService);
//            getChatRoom(chatRoomService);
            deleteChatRoomById(chatRoomService);
//            saveMessageWithSenderAndReceiver(messageService, userService, chatRoomService);
//            deleteMessageById(messageService);

        };
    }

    private void deleteMessageById(MessageService messageService) throws Exception{
        long id = 1;
        messageService.deleteMessageById(id);
    }

    private void saveMessageWithSenderAndReceiver(MessageService messageService, UserService userService, ChatRoomService chatRoomService){
        User senderPesho = new User("Pesho", "test123");
        User receiverGosho = new User("Gosho", "test123");
        ChatRoom diabloFansChatRoom = new ChatRoom("Diablo fans");
        diabloFansChatRoom.addUser(senderPesho);
        diabloFansChatRoom.addUser(receiverGosho);

        userService.save(senderPesho);
        userService.save(receiverGosho);
        chatRoomService.save(diabloFansChatRoom);


        Message message = new Message(senderPesho, receiverGosho, diabloFansChatRoom, "Kvo stava brat?");
        messageService.save(message);
    }


    private void deleteChatRoomById(ChatRoomService chatRoomService) throws Exception{
        long id = 1;

        chatRoomService.deleteChatRoomById(id);
    }
    private void getChatRoom(ChatRoomService chatRoomService) {

        long id = 1;

        ChatRoom chatRoom = chatRoomService.findChatRoomById(id);

        System.out.println(chatRoom);
    }

    private void saveChatRoom(ChatRoomService chatRoomService) {
        ChatRoom chatRoom = new ChatRoom("Programming techniques");
        chatRoomService.save(chatRoom);
    }


    private void saveUser(UserService userService) {

        User user = new User("Pesho", "test");

        userService.save(user);

    }

    private void getUser(UserService userService) {
        long id = 1;
        User user = userService.findUserById(id);
        System.out.println(user);
    }


    private void deleteUserById(UserService userService) throws Exception {
        long id = 2;
        userService.deleteById(id);
    }
}
