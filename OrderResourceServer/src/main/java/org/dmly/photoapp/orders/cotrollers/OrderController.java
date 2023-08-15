package org.dmly.photoapp.orders.cotrollers;

import lombok.RequiredArgsConstructor;
import org.dmly.photoapp.orders.dto.OrderDto;
import org.dmly.photoapp.orders.services.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

}
