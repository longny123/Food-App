package com.example.foodapp.dtos;

public class FillingDto {
    private String name ;
    private Double price;
    public FillingDto(String name, Double price) {
        this.name = name;
        this.price = price;
    }
    public FillingDto() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    

}
