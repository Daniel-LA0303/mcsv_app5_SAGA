package com.core.apis.coreapis.commands;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateShippingCommand {

    @TargetAggregateIdentifier //with this annotation, Axon will know which aggregate to send the command to
    private String shippingId;

    private String orderId;

    private String paymentId;
}
