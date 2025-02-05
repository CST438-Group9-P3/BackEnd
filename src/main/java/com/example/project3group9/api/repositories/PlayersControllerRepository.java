package com.example.project3group9.api.repositories;

import com.example.project3group9.api.model.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayersControllerRepository extends JpaRepository<Players, Integer>{
    Optional<Players> findByPlayerId(Integer playerId);
}
