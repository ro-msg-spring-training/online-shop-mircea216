package ro.msg.learning.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ShopApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test-single.properties")
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class SingleStrategyIntegratedTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private StockRepository stockRepository;
    private OrderDto orderDto;
    private Customer customer;
    private OrderDto orderDtoFailure;

    @Before
    public void setUpTest() throws Exception {
        mockMvc.perform(post("/data/populate")).andExpect(status().isOk());
        Optional<Customer> customerOptional = customerRepository.findAll().stream().findFirst();
        customerOptional.ifPresent(value -> customer = value);
        CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(),
                customer.getUsername(), customer.getEmailAddress());
        Optional<Product> product = productRepository.findAll()
                .stream()
                .findFirst();
        Integer productId = 0;
        if (product.isPresent())
            productId = product.get().getId();
        StockDto stockDto = new StockDto(productId, 3);
        List<StockDto> orderedProducts = new ArrayList<>();
        orderedProducts.add(stockDto);
        Integer locationId = 0;
        Optional<Location> location = locationRepository.findAll()
                .stream()
                .findFirst();
        if (location.isPresent())
            locationId = location.get().getId();
        orderDto = new OrderDto(LocalDate.now(), locationId, location.get().getName(),
                location.get().getCountry(), location.get().getCity(),
                location.get().getCounty(), location.get().getStreetAddress(), customerDto, orderedProducts);
        List<StockDto> orderedProductsFailure = new ArrayList<>();
        StockDto stockDtoFailure = new StockDto(productId, 300);
        orderedProductsFailure.add(stockDtoFailure);
        orderDtoFailure = new OrderDto(LocalDate.now(), locationId, location.get().getName(),
                location.get().getCountry(), location.get().getCity(),
                location.get().getCounty(), location.get().getStreetAddress(), customerDto, orderedProductsFailure);
    }

    @After
    public void tearDownTest() throws Exception {
        mockMvc.perform(delete("/data/depopulate")).andExpect(status().isOk());
    }

    @Test
    public void testCreateOrderSuccess() throws Exception {
        mockMvc.perform(post("/orders")
                        .content(jsonStringify(orderDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        assertThat(stockRepository.findAll()
                .stream()
                .findFirst()
                .get()
                .getQuantity())
                .isEqualTo(12);
    }

    @Test
    public void testCreateOrderFailure() throws NestedServletException, Exception {
        mockMvc.perform(post("/orders").content(jsonStringify(orderDtoFailure))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    public static String jsonStringify(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
