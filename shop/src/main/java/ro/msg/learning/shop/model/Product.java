package ro.msg.learning.shop.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "product")
public class Product extends MainEntity<Integer> {
    private String name;
    private String description;
    private BigInteger price;
    private Double weight;
    @ManyToOne
    @JoinColumn(name = "supplier")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "category")
    private ProductCategory productCategory;
    private String imageUrl;

    @OneToMany(mappedBy = "productOrder")
    private List<OrderDetail> orders;
    @OneToMany(mappedBy = "productStock")
    private List<Stock> stocks;

    public Product(String name, String description, BigInteger price, Double weight,
                   Supplier supplier, ProductCategory productCategory, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.supplier = supplier;
        this.productCategory = productCategory;
        this.imageUrl = imageUrl;
    }
}
