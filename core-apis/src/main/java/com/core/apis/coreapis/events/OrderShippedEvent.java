package com.core.apis.coreapis.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderShippedEvent {

        private String shippingId;
        private String orderId;
        private String paymentId;

}
