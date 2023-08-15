package org.dmly.photoapp.orders.services;

import org.dmly.photoapp.orders.dto.OrderDto;
import org.dmly.photoapp.orders.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StubOrderService implements OrderService {

    @Override
    public List<OrderDto> getOrders() {
        return List.of(
                new OrderDto(
                        UUID.randomUUID().toString(),
                        "product-id-1",
                        "user-id-1",
                        1,
                        OrderStatus.NEW
                ),
                new OrderDto(
                        UUID.randomUUID().toString(),
                        "product-id-2",
                        "user-id-1",
                        1,
                        OrderStatus.NEW
                )
        );
    }
}
