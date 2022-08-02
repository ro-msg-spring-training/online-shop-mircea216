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
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ShopApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test-abundant.properties")
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class MostAbundantStrategyIntegratedTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerRepository customerRepository;
    private OrderDto orderDto;
    private Customer customer;

    @Before
    public void setUpTest() throws Exception {
        mockMvc.perform(post("/data/populate")).andExpect(status().isOk());
        Optional<Customer> customerOptional = customerRepository.findAll().stream().findFirst();
        customerOptional.ifPresent(value -> customer = value);
        CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(),
                customer.getUsername(), customer.getEmailAddress());
        StockDto stockDto = new StockDto(1, 3);
        List<StockDto> orderedProducts = new ArrayList<>();
        orderedProducts.add(stockDto);
        orderDto = new OrderDto(LocalDate.now(), 1, "emag", "ro", "cj",
                "cj", "central", customerDto, orderedProducts);
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
    }

    public static String jsonStringify(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
