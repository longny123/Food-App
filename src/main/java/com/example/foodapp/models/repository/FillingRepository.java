package com.example.foodapp.models.repository;

import com.example.foodapp.models.entities.Filling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FillingRepository extends JpaRepository<Filling,Long> {
    
}
