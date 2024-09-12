package com.example.demo.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderFoodId implements Serializable {

    private Long idFood;
    private Long idOrder;

    public OrderFoodId() {
    }

    public OrderFoodId(Long idFood, Long idOrder) {
        this.idFood = idFood;
        this.idOrder = idOrder;
    }

    // Getters and Setters
    public Long getIdFood() {
        return idFood;
    }

    public void setIdFood(Long idFood) {
        this.idFood = idFood;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderFoodId that = (OrderFoodId) o;
        return Objects.equals(idFood, that.idFood) && Objects.equals(idOrder, that.idOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFood, idOrder);
    }
}
