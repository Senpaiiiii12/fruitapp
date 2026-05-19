package com.example.fruitapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class FruitDTO {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Min(value = 0, message = "Quantity must be >= 0")
    private Integer quantity;

    @Min(value = 0, message = "Price must be >= 0")
    private Double price;

    private String description;

    public FruitDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}