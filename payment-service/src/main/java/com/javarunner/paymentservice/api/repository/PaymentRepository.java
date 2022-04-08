package com.javarunner.paymentservice.api.repository;

import com.javarunner.paymentservice.api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    Payment findByOrderId(Integer orderId);
}
