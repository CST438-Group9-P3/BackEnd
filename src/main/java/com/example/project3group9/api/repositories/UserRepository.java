package com.example.project3group9.api.repositories;

import com.example.project3group9.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailAndUsername(String email, String username);
    Optional<User> findByUser_id(Integer user_id);
}
