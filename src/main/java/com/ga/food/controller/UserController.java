package com.ga.food.controller;

import com.ga.food.model.User;
import com.ga.food.model.request.LoginRequest;
import com.ga.food.model.response.LoginResponse;
import com.ga.food.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/auth/users")
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public User createUser(@RequestBody User userObject){
        System.out.println("Calling createUser ==> ");
        return userService.createUser(userObject);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        System.out.println("Calling loginUser ==> ");
        return userService.loginUser(loginRequest);
    }
}