package ro.msg.learning.shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate createdAt;
    private Integer locationId;
    private String name;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
    private CustomerDto customer;
    private List<StockDto> orderedProducts;

}
