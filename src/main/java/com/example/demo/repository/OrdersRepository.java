package com.example.demo.repository;

import com.example.demo.model.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Orders;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Long userId);
}
