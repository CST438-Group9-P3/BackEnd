package com.example.project3group9.api.controller;

import com.example.project3group9.api.model.Players;
import com.example.project3group9.api.model.User;
import com.example.project3group9.api.repositories.PlayersControllerRepository;
import com.example.project3group9.api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
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

    @GetMapping("/getBalance")
    public Double getBalance(@RequestParam Integer userId){
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if(userOptional.isPresent()){
            return userOptional.get().getAccount_balance();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User or Player not found");
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
