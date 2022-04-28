package com.yyit.mss.ch02.sample03.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.yyit.mss.ch02.sample03.exceptions.OrderNotFoundException;
import com.yyit.mss.ch02.sample03.orderentity.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrderProcesingService {

    private Map<String, Order> orders = new HashMap<>();

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {

        System.out.println("接收到订单 " + order.getItems().size() + " 项");
        order.getItems().forEach((lineItem) -> System.out.println("订单项: " + lineItem.getItemCode() +
                " 数量: " + lineItem.getQuantity()));

        String orderId = UUID.randomUUID().toString();
        order.setOrderId(orderId);
        orders.put(orderId, order);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) throws OrderNotFoundException {

        if (orders.containsKey(id)) {
            return new ResponseEntity<Order>(orders.get(id), HttpStatus.OK);
        } else {
            throw new OrderNotFoundException();
        }
    }
}
