package com.example.project3group9.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class OttersMenBasketBall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer player_id;

    @Column(name = "name")
    private String name;

    @Column(name = "team")
    private String team;

    @Column(name = "position")
    private String position;



    public OttersMenBasketBall() {
    }

    public Integer getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
}