package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductToSaveDto {
    private Integer id;
    private String name;
    private String description;
    private BigInteger price;
    private Double weight;
    private String imageUrl;
    private Integer productCategoryId;
    private Integer supplierId;
}
