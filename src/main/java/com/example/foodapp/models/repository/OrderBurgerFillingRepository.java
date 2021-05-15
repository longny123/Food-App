package com.example.foodapp.models.repository;


import com.example.foodapp.models.entities.OrderBurgerFilling;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBurgerFillingRepository  extends JpaRepository<OrderBurgerFilling,Long>{
    
}
