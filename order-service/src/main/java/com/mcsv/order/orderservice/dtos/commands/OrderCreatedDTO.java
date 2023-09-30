package com.mcsv.order.orderservice.dtos.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderCreatedDTO {

    private String itemType;
    private BigDecimal price;
    private String currency;
}
