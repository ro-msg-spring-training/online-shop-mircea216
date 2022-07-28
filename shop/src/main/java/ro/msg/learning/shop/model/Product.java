package ro.msg.learning.shop.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
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
    private String imageURL;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Order> orders;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Stock> stocks;
}
