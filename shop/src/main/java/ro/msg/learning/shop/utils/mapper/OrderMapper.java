package ro.msg.learning.shop.utils.mapper;

import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Order;

public class OrderMapper {
    public static Order DtoToOrder(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }
        Location location = getLocationFromDto(orderDto);
        Customer customer = getCustomerFromDto(orderDto);
        return new Order(location, customer, orderDto);
    }

    private static Location getLocationFromDto(OrderDto orderDto) {
        return new Location(orderDto);
    }

    private static Customer getCustomerFromDto(OrderDto orderDto) {
        return new Customer(orderDto);
    }
}
