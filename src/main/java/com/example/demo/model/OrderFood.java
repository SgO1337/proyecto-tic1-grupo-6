package com.example.demo.model;

import jakarta.persistence.*;


@Entity
public class OrderFood {
    @Id
    @ManyToOne
    @JoinColumn(name = "idFood")
    private Food food;

    private int quantity;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

