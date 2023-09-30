package com.core.apis.coreapis.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateInvoiceCommand {

    @TargetAggregateIdentifier //with this annotation, Axon will know which aggregate to send the command to
    private String paymentId;
    private String orderId;
}
