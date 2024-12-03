package com.example.project3group9.api.controller;

import com.example.project3group9.api.model.Transaction;
import com.example.project3group9.api.model.User;
import com.example.project3group9.api.repositories.TransactionRepository;
import com.example.project3group9.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/transactions")
    public List<Transaction> transactions() {
        return transactionRepository.findAll();
    }

    @PostMapping("/transaction")
    public Transaction transaction(@RequestParam Integer user_id, @RequestParam String type, @RequestParam Double amount, @RequestParam Date timestamp) {
        Optional<User> userOptional = userRepository.findByUser_Id(user_id);
        if (userOptional.isPresent()) {
            Transaction transaction = new Transaction();
            transaction.setUser(userOptional.get());
            transaction.setType(type);
            transaction.setAmount(amount);
            transaction.setTimestamp(timestamp);
            if(type.equals("withdraw")){
                userOptional.get().setAccountBalance(userOptional.get().getAccount_balance() - amount);
            } else if(type.equals("deposit")){
                userOptional.get().setAccountBalance(userOptional.get().getAccount_balance() + amount);
            }
            return transactionRepository.save(transaction);
        }
        throw new RuntimeException("User not found");
    }
}
