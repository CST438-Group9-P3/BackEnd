package com.example.project3group9.api.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "player")
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer playerId;

    @Column(name = "name")
    private String name;

    @Column(name = "team")
    private String team;

    @Column(name = "position")
    private String position;

    @Column(name = "player_stats")
    private String player_stats;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "player")
    private Set<Pick> picks;

    public Players() {
    }

    public Integer getPlayer_id() {
        return playerId;
    }

    public void setPlayer_id(Integer playerId) {
        this.playerId = playerId;
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
    public String getPlayer_stats() {
        return player_stats;
    }
    public void setPlayer_stats(String player_stats) {
        this.player_stats = player_stats;
    }

    public String getImage(){return image;}

    public void setImage(String image) {
        this.image = image;
    }
}