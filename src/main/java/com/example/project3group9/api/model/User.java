package com.example.project3group9.api.model;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer user_id;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="account_balance")
    private Double account_balance;

//    @OneToMany(mappedBy = "user")
//    Set<Pick> wishlists;

    public User() {};

    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Double getAccount_balance(){
        return account_balance;
    }
    public void setAccountBalance(Double account_balance){
        this.account_balance = account_balance;
    }
}
