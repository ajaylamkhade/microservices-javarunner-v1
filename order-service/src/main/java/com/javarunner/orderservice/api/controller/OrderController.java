package com.javarunner.orderservice.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javarunner.orderservice.api.common.Payment;
import com.javarunner.orderservice.api.common.TransactionRequest;
import com.javarunner.orderservice.api.common.TransactionResponse;
import com.javarunner.orderservice.api.service.OrderService;
import com.javarunner.orderservice.api.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/bookorder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {
        return orderService.saveOrder(request);
      }

}
