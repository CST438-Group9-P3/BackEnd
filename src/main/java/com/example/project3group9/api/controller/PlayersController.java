package com.example.project3group9.api.controller;
import  com.example.project3group9.api.model.Players;
import  com.example.project3group9.api.repositories.PlayersControllerRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class PlayersController {
    private final PlayersControllerRepository playersControllerRepository;

    @Autowired

    public PlayersController(PlayersControllerRepository playersControllerRepository){
        this.playersControllerRepository = playersControllerRepository;
    }
    @GetMapping("/players")
        public List<Players> players(){return playersControllerRepository.findAll();}}

