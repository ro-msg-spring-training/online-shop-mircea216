package ro.msg.learning.shop.controller;

import org.springframework.http.ResponseEntity;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.Order;

public interface OrderController {
    ResponseEntity<Order> createOrder(OrderDto orderDto);
}
