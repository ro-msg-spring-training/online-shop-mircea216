package ro.msg.learning.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.service.OrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test-abundant.properties")
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"file:src/main/resources/application-test-abundant.properties"})
public class MostAbundantStrategyIntegratedTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUpTest() throws Exception {
        mockMvc.perform(post("/data/populate")).andExpect(status().isOk());
    }

    @AfterEach
    void tearDownTest() throws Exception {
        mockMvc.perform(delete("/data/depopulate")).andExpect(status().isOk());
    }

    @Test
    void testCreateOrderSuccess() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = customerRepository.getReferenceById(1);
        CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(),
                customer.getUsername(), customer.getEmailAddress());
        StockDto stockDto = new StockDto(1, 3);
        List<StockDto> orderedProducts = new ArrayList<>();
        orderedProducts.add(stockDto);
        OrderDto orderDto = new OrderDto(LocalDate.now(), 1, "emag", "ro", "cj",
                "cj", "central", customerDto, orderedProducts);
        mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name").value("emag"));
    }
}
