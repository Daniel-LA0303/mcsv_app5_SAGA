package com.mcsv.order.orderservice.controllers;


import com.mcsv.order.orderservice.dtos.commands.OrderCreatedDTO;
import com.mcsv.order.orderservice.services.commands.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/orders")
public class OrderCommandController {

    @Autowired
    private OrderCommandService orderCommandService;

    @PostMapping
    public CompletableFuture<String> createOrder(@RequestBody OrderCreatedDTO orderCreatedDTO){
        return orderCommandService.createOrder(orderCreatedDTO);
    }
}
