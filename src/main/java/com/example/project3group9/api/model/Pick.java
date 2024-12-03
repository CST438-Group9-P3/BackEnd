package com.example.project3group9.api.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "pick")
public class Pick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pick_id")
    private Integer pick_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Players player;

    @Column(name="selection")
    private String selection;

    @Column(name="stake")
    private Double stake;

    @Column(name="target_value")
    private Double targetValue;

    @Column(name="player_value")
    private Double playerValue;

    @Column(name="timestamp")
    private Date timestamp;

    public Pick (){};

    public Integer getPick_id() {
        return pick_id;
    }
    public void setPick_id(Integer pick_id) {
        this.pick_id = pick_id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Players getPlayer() {
        return player;
    }
    public void setPlayer(Players player) {
        this.player = player;
    }
    public String getSelection() {
        return selection;
    }
    public void setSelection(String selection) {
        this.selection = selection;
    }
    public Double getStake() {
        return stake;
    }
    public void setStake(Double stake) {
        this.stake = stake;
    }
    public Double getTargetValue() {
        return targetValue;
    }
    public void setTargetValue(Double targetValue) {
        this.targetValue = targetValue;
    }
    public Double getPlayerValue() {
        return playerValue;
    }
    public void setPlayerValue(Double playerValue) {
        this.playerValue = playerValue;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
