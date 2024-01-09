package com.dycheto.chatapp.controller;


import com.dycheto.chatapp.entity.User;
import com.dycheto.chatapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        logger.debug("Register request received for user : ", user.getUsername());

        if(userService.existsByUsername(user.getUsername())){
            logger.debug("Username already exists:  : ", user.getUsername());
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        User newUser =  userService.registerNewUser(user);

        logger.debug("User registered successfully: ", newUser.getUsername());

        return ResponseEntity.ok("User registered successfully!");
    }


}
