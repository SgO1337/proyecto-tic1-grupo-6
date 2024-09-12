package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFood;

    private String name;
    private String type;
    private double price;

    @OneToMany(mappedBy = "food")
    private List<OrderFood> orders;

    // Getters and Setters...
}
