package com.javarunner.orderservice.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarunner.orderservice.api.common.Payment;
import com.javarunner.orderservice.api.common.TransactionRequest;
import com.javarunner.orderservice.api.common.TransactionResponse;
import com.javarunner.orderservice.api.entity.Order;
import com.javarunner.orderservice.api.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;
    private Logger log = LoggerFactory.getLogger(OrderService.class);


    public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
        String response="";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        log.info("order service request : {} ",new ObjectMapper().writeValueAsString(request));
        //rest call
      Payment paymentResponse = restTemplate.postForObject(ENDPOINT_URL,payment,Payment.class);
        log.info("payment service response from oder service rest call : {} ",new ObjectMapper().writeValueAsString(paymentResponse));
        System.out.println("ENDPOINT_URL: "+ENDPOINT_URL);
      response= paymentResponse.getPaymentStatus().equals("success")?"payment procession successful and order placed":"there is a failure in payment api,oder added to cart";
      orderRepository.save(order);
      return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);


    }
}
