package com.mcsv.order.orderservice.aggregates;


import com.core.apis.coreapis.commands.CreateOrderCommands;
import com.core.apis.coreapis.commands.UpdateOrderStatusCommand;
import com.core.apis.coreapis.events.OrderCreatedEvent;
import com.core.apis.coreapis.events.OrderUpdateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
public class OrderAggregate {


    @AggregateIdentifier
    private String orderId;

    private ItemType itemType;

    private BigDecimal price;

    private String currency;

    private OrderStatus orderStatus;

    //constructors
    public OrderAggregate() {
    }


    //---- CREATED ORDER ----//
    @CommandHandler
    public OrderAggregate(CreateOrderCommands createOrderCommands) {

        AggregateLifecycle.apply(new OrderCreatedEvent(
                createOrderCommands.getOrderId(),
                createOrderCommands.getItemType(),
                createOrderCommands.getPrice(),
                createOrderCommands.getCurrency(),
                createOrderCommands.getOrderStatus()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        this.orderId = orderCreatedEvent.getOrderId();
        this.itemType = ItemType.valueOf(orderCreatedEvent.getItemType());
        this.price = orderCreatedEvent.getPrice();
        this.currency = orderCreatedEvent.getCurrency();
        this.orderStatus = OrderStatus.valueOf(orderCreatedEvent.getOrderStatus());
    }


    //---- UPDATE ORDER ----//
    @CommandHandler
    public void on(UpdateOrderStatusCommand updateOrderStatusCommand) {
        AggregateLifecycle.apply(new OrderUpdateEvent(
                updateOrderStatusCommand.getOrderId(),
                updateOrderStatusCommand.getOrderStatus()));
    }

    @EventSourcingHandler
    public void on(OrderUpdateEvent orderUpdateEvent) {
        this.orderId = orderUpdateEvent.getOrderId();
        this.orderStatus = OrderStatus.valueOf(orderUpdateEvent.getOrderStatus());
    }
}
