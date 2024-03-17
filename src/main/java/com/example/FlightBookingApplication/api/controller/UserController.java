package com.example.FlightBookingApplication.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.FlightBookingApplication.api.models.Users;
import com.example.FlightBookingApplication.api.repository.UserRepository;
import com.example.FlightBookingApplication.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private UserRepository userRepository;
 
    public UserController(UserService userService) {
        this.userService = userService;
    }
 
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestParam String userId, @RequestParam String name, @RequestParam double funds) {
        userService.addUser(userId, name, funds);
        return ResponseEntity.ok("User Created");
    }
 
    @GetMapping("/details")
    public Users getUserDetails(@RequestParam String userId) {
        return userRepository.getUserById(userId);
    }
}