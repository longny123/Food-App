package com.example.foodapp.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS_BURGER_FILLINGS")
public class OrderBurgerFilling {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Integer quantity;


    @ManyToOne
    @JoinColumn(name="burger_id", nullable = false)
    private OrderBurger burger;

    @ManyToOne
    @JoinColumn(name="filling_id",nullable = false)
    private Filling filling;

    public OrderBurgerFilling(Integer quantity, OrderBurger burger, Filling filling) {
        this.quantity = quantity;
        this.burger = burger;
        this.filling = filling;
    }

    public OrderBurgerFilling() {
    }

    public Long getId() {
        return id;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderBurger getBurger() {
        return burger;
    }

    public void setBurger(OrderBurger burger) {
        this.burger = burger;
    }

    public Filling getFilling() {
        return filling;
    }

    public void setFilling(Filling filling) {
        this.filling = filling;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
