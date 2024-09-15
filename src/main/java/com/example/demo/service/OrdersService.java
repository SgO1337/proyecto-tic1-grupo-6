package com.example.demo.service;

import com.example.demo.model.Orders;
import com.example.demo.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    // Create a new order
    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }

    // Cancel an existing order
    public Orders cancelOrder(Long id) {
        Optional<Orders> optionalOrder = ordersRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();
            order.setIsCancelled(true);
            return ordersRepository.save(order);
        }
        return null; // or throw custom exception
    }

    // Get all orders for a specific user
    public List<Orders> getOrdersByUser(Long userId) {
        return ordersRepository.findByUserId(userId); // Adapt based on your actual user relationship
    }

    // Update an existing order
    public Orders updateOrder(Long id, Orders updatedOrder) {
        Optional<Orders> optionalOrder = ordersRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Orders existingOrder = optionalOrder.get();
            existingOrder.setDate(updatedOrder.getDate());
            existingOrder.setDelivered(updatedOrder.getDelivered());
            existingOrder.setIsCancelled(updatedOrder.getIsCancelled());
            existingOrder.setOrderFood(updatedOrder.getOrderFood()); // Handle updates for OrderFood
            return ordersRepository.save(existingOrder);
        }
        return null; // or throw custom exception
    }
}
