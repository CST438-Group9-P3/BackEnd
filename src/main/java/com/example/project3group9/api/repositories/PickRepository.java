package com.example.project3group9.api.repositories;

import com.example.project3group9.api.model.Pick;
import com.example.project3group9.api.model.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickRepository extends JpaRepository<Pick, Integer> {
}
