package com.mcsv.order.orderservice.services.commands;

import com.mcsv.order.orderservice.dtos.commands.OrderCreatedDTO;

import java.util.concurrent.CompletableFuture;

public interface OrderCommandService {


    public CompletableFuture<String> createOrder(OrderCreatedDTO orderCreatedDTO);
}
