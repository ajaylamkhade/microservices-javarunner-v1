package com.javarunner.paymentservice.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javarunner.paymentservice.api.entity.Payment;
import com.javarunner.paymentservice.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        System.out.println("doPayment end service called !");
            return paymentService.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable Integer orderId) throws JsonProcessingException {
       return paymentService.findPaymentHistoryByOrderId(orderId);
    }
}
