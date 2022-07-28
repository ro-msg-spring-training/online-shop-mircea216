package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Order extends MainEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    private LocalDate date;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
}
