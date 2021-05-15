package com.example.foodapp.models.repository;

import org.springframework.stereotype.Repository;

import com.example.foodapp.models.entities.Order;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    
}
