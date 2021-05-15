package com.example.foodapp.models.entities;

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
@Table(name = "ORDERS_BURGERS")
public class OrderBurger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Order order; 

    @OneToMany(mappedBy = "burger",orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<OrderBurgerFilling> fillings ;

    public OrderBurger(Integer quantity, Order order) {
        this.setQuantity(quantity);
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderBurger() {
    }

    public Long getId() {
        return id;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Set<OrderBurgerFilling> getFillings() {
        return fillings;
    }

    public void setFillings(Set<OrderBurgerFilling> fillings) {
        this.fillings = fillings;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
