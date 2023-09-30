package com.core.apis.coreapis.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//--- This class is a command

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrderCommands {


    private String orderId;
    private String itemType;
    private BigDecimal price;
    private String currency;
    private String orderStatus;
}
