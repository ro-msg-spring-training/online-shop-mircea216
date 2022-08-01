package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.Order;

public interface OrderService {
    Order createOrder(OrderDto orderDto);
}
