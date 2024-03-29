package com.dycheto.chatapp.controller;

import com.dycheto.chatapp.dto.ChatRoomRequest;
import com.dycheto.chatapp.dto.ChatRoomResponse;
import com.dycheto.chatapp.entity.ChatRoom;
import com.dycheto.chatapp.entity.User;
import com.dycheto.chatapp.exeption.ChatRoomAlreadyExistsException;
import com.dycheto.chatapp.service.ChatRoomService;
import com.dycheto.chatapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);

    public ChatRoomController(ChatRoomService chatRoomService, UserService userService) {
        this.chatRoomService = chatRoomService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createChatRoomForUser(@RequestBody ChatRoomRequest chatRoomRequest){

        if (chatRoomRequest.getName() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error: ChatRoom must have a valid name."));
        }

        if (chatRoomRequest.getUserId() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error: User id must be provided."));
        }

        String chatRoomName = chatRoomRequest.getName();
        Long chatRoomOwnerId = chatRoomRequest.getUserId();
        User user = userService.findUserById(chatRoomOwnerId);
        ChatRoom chatRoom = new ChatRoom(chatRoomName, chatRoomOwnerId);
        chatRoom.addUser(user);

        try{
            chatRoomService.save(chatRoom);
        }catch (ChatRoomAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
        }


        ChatRoom dbRoom = chatRoomService.getChatRoomByName(chatRoomName);
        ChatRoomResponse response = new ChatRoomResponse(
                dbRoom.getId(),
                dbRoom.getName(),
                dbRoom.getOwnerId(),
                dbRoom.getCreatedAt(),
                dbRoom.isActive(),
                dbRoom.isPrivate());

        return ResponseEntity.ok(response);
    }

}
