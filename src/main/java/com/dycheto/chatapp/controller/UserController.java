package com.dycheto.chatapp.controller;

import com.dycheto.chatapp.dto.*;
import com.dycheto.chatapp.entity.ChatRoom;
import com.dycheto.chatapp.entity.User;
import com.dycheto.chatapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/me")
    public ResponseEntity<?> getUser(Principal principal) throws  Exception{

        String currentUsername = principal.getName();
        Optional<User> userOptional = userService.findByUsernameWithChatrooms(currentUsername);

        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();

        UserResponse response = new UserResponse(user.getId(), user.getUsername());

        for(ChatRoom chatRoom : user.getChatRooms()) {
            ChatRoomDTO chatRoomDTO = convertToDTO(chatRoom);
            response.addChatRoom(chatRoomDTO);
        }

        return ResponseEntity.ok(response);
    }

    private ChatRoomDTO convertToDTO(ChatRoom chatRoom) {
        // Create a new ChatRoomDTO and set the necessary fields
        ChatRoomDTO dto = new ChatRoomDTO();
        dto.setId(chatRoom.getId());
        dto.setName(chatRoom.getName());
        // set other fields as needed
        return dto;
    }
}
