package com.mcsv.order.orderservice.sagas;

import com.core.apis.coreapis.commands.CreateInvoiceCommand;
import com.core.apis.coreapis.commands.CreateShippingCommand;
import com.core.apis.coreapis.commands.UpdateOrderStatusCommand;
import com.core.apis.coreapis.events.InvoiceCreatedEvent;
import com.core.apis.coreapis.events.OrderCreatedEvent;
import com.core.apis.coreapis.events.OrderShippedEvent;
import com.core.apis.coreapis.events.OrderUpdateEvent;
import com.mcsv.order.orderservice.aggregates.OrderStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga //this annotation is used to mark this class as a saga and this saga is associated with a aggregate in this case OrderAggregate
public class OrderManagementSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga //this annotation say to axon that init a new saga
    @SagaEventHandler(associationProperty = "orderId") //this annotation say to axon that this method is a event handler and the associationProperty is the property that will be used to associate the saga with the aggregate in this case the orderId
    public void handle(OrderCreatedEvent orderCreatedEvent){
        String paymentId = UUID.randomUUID().toString();
        System.out.println("SAGA invoked");

        //we associate the SAGA
        SagaLifecycle.associateWith("paymentId", paymentId);

        System.out.println("Order ID: "+ orderCreatedEvent.getOrderId());

        //send the commands
        commandGateway.send(
                new CreateInvoiceCommand(
                        paymentId,
                        orderCreatedEvent.getOrderId()
                )
        );

    }

    @SagaEventHandler(associationProperty = "paymentId")
    public void handle(InvoiceCreatedEvent invoiceCreatedEvent){
        String shippingId = UUID.randomUUID().toString();

        System.out.println("SAGA Continue");

        //we associate the SAGA with shipping service
        SagaLifecycle.associateWith("shippingId", shippingId);

        //we send the command to shipping
        commandGateway.send(
                new CreateShippingCommand(
                        shippingId,
                        invoiceCreatedEvent.getOrderId(),
                        invoiceCreatedEvent.getPaymentId()
                )
        );
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderShippedEvent orderShippedEvent){
        commandGateway.send(
                new UpdateOrderStatusCommand(
                        orderShippedEvent.getOrderId(),
                        String.valueOf(OrderStatus.SHIPPED)
                )
        );
    }

    //---- Here we end the saga ----//
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderUpdateEvent orderShippedEvent){
        SagaLifecycle.end();
    }
}
