package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth management", description = "Api for creating users and log in in the app")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create user", description = "Create a new user with his role in the app")
    @PostMapping("/sign-up")
    public ResponseEntity<User> createUser(@RequestBody User user){
        userService.addUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Get specific user credentials", description = "Provide authorization to access the app while finding a user")
    @GetMapping("/login")
    public User getUser(String userName, String password){
        User foundUser = userService.getUserByCredentials(userName, password);
        return foundUser;
    }

}
