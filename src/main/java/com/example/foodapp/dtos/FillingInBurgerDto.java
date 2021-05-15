package com.example.foodapp.dtos;

import com.example.foodapp.models.entities.Filling;

public class FillingInBurgerDto {
    private Long id;
    private Integer quantity;
    private Filling filling;
    public FillingInBurgerDto() {
    }
    public Filling getFilling() {
        return filling;
    }
    public void setFilling(Filling filling) {
        this.filling = filling;
    }
    public FillingInBurgerDto(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public FillingInBurgerDto(Long id, Integer quantity, Filling filling) {
        this.id = id;
        this.quantity = quantity;
        this.filling = filling;
    }
}
