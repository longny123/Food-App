package com.example.foodapp.models.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private LocalDate startDate;

    @OneToMany(mappedBy = "order",orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<OrderBurger> burgers ;


    public Order(UserEntity user, Boolean status, LocalDate startDate) {
        this.user = user;
        this.status = status;
        this.startDate = startDate;
    }



    public Order() {
    }

    public Long getId() {
        return id;
    }


    public UserEntity getUserid() {
        return user;
    }

    public void setUserid(UserEntity user) {
        this.user = user;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public Set<OrderBurger> getBurgers() {
        return burgers;
    }



    public void setBurgers(Set<OrderBurger> burgers) {
        this.burgers = burgers;
    }

    
}
