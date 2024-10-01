package com.passgenius.serviceuser.controller;



import com.passgenius.serviceuser.exceptions.IncorrectPassowrdrException;
import com.passgenius.serviceuser.exceptions.InvalidAuthorizationHeaderException;
import com.passgenius.serviceuser.exceptions.UserAlreadyExistException;
import com.passgenius.serviceuser.exceptions.UserNotFoundException;
import com.passgenius.serviceuser.models.User2;
import com.passgenius.serviceuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestBody User2 user2,
            @RequestHeader("Authorization") String authorizationHeader
    ) {

        try {
            userService.registerUser(user2, authorizationHeader);
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body("User already exist");
        } catch (InvalidAuthorizationHeaderException e) {
            return ResponseEntity.badRequest().body("Invalid authorization header!");
        }

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        try {
            var user = userService.loginUser(authorizationHeader);
            return ResponseEntity.ok(user);
        } catch (InvalidAuthorizationHeaderException e) {
            return ResponseEntity.badRequest().body("Invalid authorization header");
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("User doesn't exist!");
        } catch (IncorrectPassowrdrException e) {
            return ResponseEntity.badRequest().body("Incorrect password!");
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("User registered successfully");
    }

}

