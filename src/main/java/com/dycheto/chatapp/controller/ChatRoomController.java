package com.dycheto.chatapp.controller;

import com.dycheto.chatapp.dto.ChatRoomRequest;
import com.dycheto.chatapp.dto.ChatRoomResponse;
import com.dycheto.chatapp.entity.ChatRoom;
import com.dycheto.chatapp.entity.User;
import com.dycheto.chatapp.service.ChatRoomService;
import com.dycheto.chatapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final UserService userService;

    public ChatRoomController(ChatRoomService chatRoomService, UserService userService) {
        this.chatRoomService = chatRoomService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public ResponseEntity<?> createChatRoomForUser(@RequestBody ChatRoomRequest chatRoomRequest) {

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
        chatRoomService.save(chatRoom);
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
