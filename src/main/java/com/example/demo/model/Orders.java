package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @Column(nullable = false)
    private Boolean isCancelled = false;

    @Column(nullable = false)
    private Boolean isDelivered = false;

    private Date date;

    @OneToMany(mappedBy = "order")
    private List<OrderFood> orderFood;

    // Constructor por defecto
    public Orders() {
        this.isCancelled = false; //es falso por defecto, hasta que se cancele

        this.isDelivered = false; //es falso por defecto, hasta que se entregue
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderFood> getOrderFood() {
        return orderFood;
    }

    public void setOrderFood(OrderFood orderFood) {
        this.orderFood.add(orderFood);
    }

    public Boolean getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public Boolean getCancelled() {
        return isCancelled;
    }

    public void setCancelled(Boolean cancelled) {
        isCancelled = cancelled;
    }

    public Boolean getDelivered() {
        return isDelivered;
    }

    public void setDelivered(Boolean delivered) {
        isDelivered = delivered;
    }

    public void setOrderFood(List<OrderFood> orderFood) {
        this.orderFood = orderFood;
    }
}



