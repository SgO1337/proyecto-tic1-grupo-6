package com.example.demo.controller;

import com.example.demo.model.Orders;
import com.example.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

//este controlador se encarga de:
/*
-crear una order, con sus respectivos objetos orderfood, que pueden variados
-cancelar una orden (baja logica)
-ver todas las ordenes de un usuario

-opcional: modificar orden
*/

@RestController  // Cambio a RestController para respuestas en JSON
@RequestMapping("/api/orders")  // Cambia la URL base para empezar con /api
@CrossOrigin(origins = "http://localhost:3000")  // Permitir CORS para tu aplicación React
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    // Create an order with its associated order foods
    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        Orders savedOrder = ordersService.createOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Cancel an order (logical deletion)
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Orders> cancelOrder(@PathVariable Long id) {
        Orders order = ordersService.cancelOrder(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all orders for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Orders>> getOrdersByUser(@PathVariable Long userId) {
        List<Orders> orders = ordersService.getOrdersByUser(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Optional: Update an existing order
    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders updatedOrder) {
        Orders order = ordersService.updateOrder(id, updatedOrder);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
