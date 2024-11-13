package com.example.project3group9.api.controller;
import  com.example.project3group9.api.model.OttersMenBasketBall;
import  com.example.project3group9.api.repositories.OttersMenBasketBallRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class OttersMenBasketBallController {
    private final OttersMenBasketBallRepository ottersMenBasketBallRepository;

    @Autowired

    public OttersMenBasketBallController(OttersMenBasketBallRepository ottersMenBasketBallRepository){
        this.ottersMenBasketBallRepository = ottersMenBasketBallRepository;
    }
    @GetMapping("/otters-men-basketball")
        public List<OttersMenBasketBall> players(){return ottersMenBasketBallRepository.findAll();}}
