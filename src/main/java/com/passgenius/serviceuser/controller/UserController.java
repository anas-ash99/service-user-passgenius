package com.passgenius.serviceuser.controller;

import com.passgenius.serviceuser.exceptions.UserNotFoundException;
import com.passgenius.serviceuser.service.UserService;
import com.passgenius.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try {
            return ResponseEntity.ok(userService.updateUser(user));
        }catch (UserNotFoundException e){
            logger.error("Error updating user: ", e);
            return ResponseEntity.badRequest().body("User doesn't exist");
        }
    }

}

