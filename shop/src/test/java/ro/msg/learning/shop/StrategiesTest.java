package ro.msg.learning.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.impl.OrderServiceImpl;
import ro.msg.learning.shop.service.strategy.OrderStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class StrategiesTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private OrderStrategy orderStrategy;
    @Mock
    private OrderDetailRepository orderDetailRepository;
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;
    private Location location;
    private Customer customer;
    private OrderDto orderDto;

    @BeforeEach
    public void setUp() {
        location = new Location("Emag", "Romania", "Cluj Napoca",
                "Cluj",
                "Centrala");
        location.setId(1);
        customer = new Customer("Alex", "Doe", "adoe", "adoe@gmail.com");
        customer.setId(1);
        CustomerDto customerDto = new CustomerDto(1, "Alex", "Doe", "adoe",
                "adoe@gmail.com");
        order = new Order(location, customer, LocalDate.now(), "Ro", "Cj", "Cj",
                "Central");
        List<StockDto> orderedProducts = new ArrayList<>();
        orderedProducts.add(new StockDto(1, 1));
        orderDto = new OrderDto(LocalDate.now(), location.getId(), location.getName(),
                location.getCountry(), location.getCity(), location.getCounty(), location.getStreetAddress(),
                customerDto, orderedProducts);
    }

    @DisplayName("JUnit test for create order method")
    @Test
    void testCreateOrder() {
        Order orderToTest = orderService.createOrder(orderDto);
        assertThat(orderToTest.getCreatedAt()).isToday();
        assertThat(orderToTest.getCustomer().getFirstName()).isEqualTo("Alex");
        assertThat(orderToTest.getCity()).isEqualTo("Cluj Napoca");
        assertThat(orderToTest.getCounty()).isEqualTo("Cluj");
        assertThat(orderToTest.getCountry()).isEqualTo("Romania");
        assertThat(orderToTest.getStreetAddress()).isEqualTo("Centrala");
    }
}
