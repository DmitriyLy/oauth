package org.dmly.photoapp.orders.services;

import org.dmly.photoapp.orders.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getOrders();
}
