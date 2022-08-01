package ro.msg.learning.shop.utils.mapper;

import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Order;

import java.time.LocalDate;

public class OrderMapper {
    public static Order DtoToOrder(OrderDto orderDto) {
        if (orderDto == null)
            return null;
        Location location = new Location(orderDto.getName(), orderDto.getCountry(), orderDto.getCity(),
                orderDto.getCounty(), orderDto.getStreetAddress());
        location.setId(orderDto.getLocationId());
        Customer customer = new Customer(orderDto.getCustomer().getFirstName(), orderDto.getCustomer().getLastName(),
                orderDto.getCustomer().getUsername(), orderDto.getCustomer().getEmailAddress());
        customer.setId(orderDto.getCustomer().getId());
        return new Order(location, customer, LocalDate.now(), orderDto.getCountry(), orderDto.getCity(),
                orderDto.getCounty(), orderDto.getStreetAddress());
    }
}
