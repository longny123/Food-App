package com.example.foodapp.models.repository;


import com.example.foodapp.models.entities.OrderBurger;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBurgerRepository extends JpaRepository<OrderBurger,Long> {
    
}
