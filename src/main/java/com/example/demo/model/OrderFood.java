package com.example.demo.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

//esta entidad guarda las comidas por orden en una cantidad x, por ejemplo (idorden,idcomida,cantidad) = (1,1,10) y (1,2,5), etc
@Entity
public class OrderFood {

    @EmbeddedId
    private OrderFoodId id;

    @ManyToOne
    @JoinColumn(name = "idFood", insertable = false, updatable = false)
    private Food food;

    @ManyToOne
    @JoinColumn(name = "idOrder", insertable = false, updatable = false)
    private Orders order;

    private int quantity;

}
