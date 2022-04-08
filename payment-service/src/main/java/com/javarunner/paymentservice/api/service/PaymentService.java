package com.javarunner.paymentservice.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarunner.paymentservice.api.entity.Payment;
import com.javarunner.paymentservice.api.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    private Logger log = LoggerFactory.getLogger(PaymentService.class);

    public Payment doPayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(payementProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        log.info("payment service request : {} ",new ObjectMapper().writeValueAsString(payment));
        return paymentRepository.save(payment);
    }

    public String payementProcessing(){
        //api should be 3rd party payment gateway (paypal ,paytm etc.)
        //random value used only for demonstration purpose
        return new Random().nextBoolean()?"success":"fail";
    }

    public Payment findPaymentHistoryByOrderId(Integer orderId) throws JsonProcessingException {
     Payment payment = paymentRepository.findByOrderId(orderId);
        log.info("PaymentService findPaymentHistoryByOrderId: {} ",new ObjectMapper().writeValueAsString(payment));
        return payment;
    }
}
