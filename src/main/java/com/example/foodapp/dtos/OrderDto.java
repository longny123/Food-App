package com.example.foodapp.dtos;

import java.time.LocalDate;
import java.util.Set;


public class OrderDto {
    private Long id; 
    private LocalDate startDate;
    private Boolean status;
    private Set<BurgerDto> burgers;
    public OrderDto() {
    }
    public OrderDto(Long id, LocalDate startDate, Boolean status, Set<BurgerDto> burgers) {
        this.id = id;
        this.startDate = startDate;
        this.status = status;
        this.burgers = burgers;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Set<BurgerDto> getBurgers() {
        return burgers;
    }
    public void setBurgers(Set<BurgerDto> burgers) {
        this.burgers = burgers;
    }

}
