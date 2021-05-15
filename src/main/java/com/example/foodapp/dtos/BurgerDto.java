package com.example.foodapp.dtos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BurgerDto {
    private Long id ;
    @JsonProperty("filling")
    private Set<FillingInBurgerDto> fillings ;
    @JsonProperty("quantity")
    private Integer quantity;
    public BurgerDto(Set<FillingInBurgerDto> fillings, Integer quantity) {
        this.fillings = fillings;
        this.quantity = quantity;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BurgerDto() {
    }
    public Set<FillingInBurgerDto> getFillings() {
        return fillings;
    }
    public void setFillings(Set<FillingInBurgerDto> fillings) {
        this.fillings = fillings;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}
