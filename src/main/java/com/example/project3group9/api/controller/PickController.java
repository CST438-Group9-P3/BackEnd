package com.example.project3group9.api.controller;

import com.example.project3group9.api.model.Pick;
import com.example.project3group9.api.model.Players;
import com.example.project3group9.api.model.Transaction;
import com.example.project3group9.api.model.User;
import com.example.project3group9.api.repositories.PickRepository;
import com.example.project3group9.api.repositories.PlayersControllerRepository;
import com.example.project3group9.api.repositories.TransactionRepository;
import com.example.project3group9.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin
@RestController
public class PickController {
    private final PickRepository pickRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final PlayersControllerRepository playersControllerRepository;

    @Autowired
    public PickController(PickRepository pickRepository, UserRepository userRepository, TransactionRepository transactionRepository, PlayersControllerRepository playersControllerRepository) {
        this.pickRepository = pickRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.playersControllerRepository = playersControllerRepository;
    }

    @GetMapping("/picks")
    public List<Pick> picks() {
        return pickRepository.findAll();
    }

    @GetMapping("/userActivePicks")
    public List<Pick> userActivePicks(@RequestParam Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            List<Pick> activePicks = new ArrayList<>();
            List<Pick> picks = pickRepository.findAll();
            for (Pick pick : picks) {
                if(pick.getStatus().equals("active") && Objects.equals(pick.getUser().getUser_id(), user.get().getUser_id())) {
                    activePicks.add(pick);
                }
            }
            return activePicks;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @GetMapping("/userPastPicks")
    public List<Pick> userPastPicks(@RequestParam Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            List<Pick> pastPicks = new ArrayList<>();
            List<Pick> picks = pickRepository.findAll();
            for (Pick pick : picks) {
                if(pick.getStatus().equalsIgnoreCase("completed") && Objects.equals(pick.getUser().getUser_id(), user.get().getUser_id())) {
                    pastPicks.add(pick);
                }
            }
            return pastPicks;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @PostMapping("/createPick")
    public Pick createPick(@RequestParam Integer userId, @RequestParam Integer playerId, @RequestParam String selection, @RequestParam Double stake, @RequestParam Double targetValue, @RequestParam Double playerValue, @RequestParam Date timestamp) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        Optional<Players> playerOptional = playersControllerRepository.findById(playerId);
        if (userOptional.isPresent() && playerOptional.isPresent()) {
            if(stake > userOptional.get().getAccount_balance()){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Stake cannot be greater than account balance");
            }
            if(stake < 0){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Stake cannot be negative");
            }
            Pick pick = new Pick();
            pick.setUser(userOptional.get());
            pick.setPlayer(playerOptional.get());
            pick.setSelection(selection);
            pick.setStake(stake);
            pick.setTargetValue(targetValue);
            pick.setPlayerValue(playerValue);
            pick.setTimestamp(timestamp);
            pick.setStatus("active");
            pick.setResult("undefined");
            return pickRepository.save(pick);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User or Player not found");
    }

    @PatchMapping("/finalizePick")
    public ResponseEntity<Pick> finalizePick(@RequestParam Integer userId, @RequestParam Integer pickId, @RequestParam Double simValue) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if(userOptional.isPresent()) {
            Optional<Pick> pickOptional = pickRepository.findByPickId(pickId);
            if(pickOptional.isPresent()) {
                if(pickOptional.get().getStatus().equalsIgnoreCase("active")){
                    Pick pick = pickOptional.get();
                    pick.setPlayerValue(simValue);
                    if(pick.getSelection().equalsIgnoreCase("over")){
                        if(pick.getTargetValue() < pick.getPlayerValue()){
                            userOptional.get().setAccountBalance(userOptional.get().getAccount_balance() + (2* pick.getStake()));
                            pick.setResult("win");
                        } else {
                            userOptional.get().setAccountBalance(userOptional.get().getAccount_balance() - pick.getStake());
                            pick.setResult("loss");
                        }
                    } else if(pick.getSelection().equalsIgnoreCase("under")){
                        if(pick.getTargetValue() < pick.getPlayerValue()){
                            userOptional.get().setAccountBalance(userOptional.get().getAccount_balance() - pick.getStake());
                            pick.setResult("loss");
                        } else {
                            userOptional.get().setAccountBalance(userOptional.get().getAccount_balance() + (2 * pick.getStake()));
                            pick.setResult("win");
                        }
                    }
                    pick.setStatus("completed");
                    pickRepository.save(pick);
                    return ResponseEntity.ok(pick);
                }
                throw new RuntimeException("Pick is not active");
            }
            throw new RuntimeException("Pick not found");
        }
        throw new RuntimeException("User not found");
    }
}
