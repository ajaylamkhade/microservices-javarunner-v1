package com.javarunner.orderservice.api.repository;

import com.javarunner.orderservice.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository <Order,Integer> {
}
