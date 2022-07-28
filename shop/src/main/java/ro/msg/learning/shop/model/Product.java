package ro.msg.learning.shop.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigInteger;

@Entity
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
}
