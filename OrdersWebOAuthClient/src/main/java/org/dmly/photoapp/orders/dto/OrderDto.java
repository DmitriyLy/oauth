package org.dmly.photoapp.orders.dto;

import org.dmly.photoapp.orders.model.OrderStatus;

public record OrderDto(
        String orderId,
        String productId,
        String userId,
        int quantity,
        OrderStatus orderStatus
) {
}
