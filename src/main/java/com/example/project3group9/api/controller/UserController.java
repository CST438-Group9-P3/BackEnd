package com.example.project3group9.api.controller;

import com.example.project3group9.api.model.User;
import com.example.project3group9.api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> users(){
        return userRepository.findAll();
    }

    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String username) {
        Optional<User> userOptional = userRepository.findByEmailAndUsername(email, username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setAccountBalance(0.0); // default value
            return userRepository.save(newUser);
        }
    }

    @DeleteMapping("/deleteAcc")
    public ResponseEntity<String> deleteAccount(@RequestParam String email, @RequestParam String username) {
        Optional<User> userOptional = userRepository.findByEmailAndUsername(email, username);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return ResponseEntity.ok("User successfully deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
