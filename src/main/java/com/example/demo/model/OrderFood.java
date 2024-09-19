package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class OrderFood {
    @Id
    @ManyToOne
    @JoinColumn(name = "food")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "orders")
    private Orders orders;
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

