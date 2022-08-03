package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Product;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private BigInteger price;
    private Double weight;
    private String imageUrl;
    private Integer productCategoryId;
    private String productCategoryName;
    private String productCategoryDescription;
    private Integer supplierId;
    private String supplierName;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.weight = product.getWeight();
        this.imageUrl = product.getImageUrl();
        this.productCategoryId = product.getProductCategory().getId();
        this.productCategoryName = product.getProductCategory().getName();
        this.productCategoryDescription = product.getProductCategory().getDescription();
        this.supplierId = product.getSupplier().getId();
        this.supplierName = product.getSupplier().getName();
    }
}
