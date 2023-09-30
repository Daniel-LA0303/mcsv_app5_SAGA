package com.mcsv.order.orderservice.services.commands;

import com.core.apis.coreapis.commands.CreateOrderCommands;
import com.mcsv.order.orderservice.aggregates.OrderStatus;
import com.mcsv.order.orderservice.dtos.commands.OrderCreatedDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OerderCommandServiceImpl implements OrderCommandService{

    @Autowired
    private CommandGateway commandGateway;

    @Override
    public CompletableFuture<String> createOrder(OrderCreatedDTO orderCreatedDTO) {
        return commandGateway.send(
                new CreateOrderCommands( //TO GET THE CLASS OF ANOTHER PROJECT WE NEED TO DO A DEPENDENCY SETUP ON THE POM OF THIS PROJECT
                        UUID.randomUUID().toString(),
                        orderCreatedDTO.getItemType(),
                        orderCreatedDTO.getPrice(),
                        orderCreatedDTO.getCurrency(),
                        String.valueOf(OrderStatus.CREATED) //TO GET THE ENUM VALUE WE NEED TO DO A CAST TO STRING
                )
            );
    }
}
