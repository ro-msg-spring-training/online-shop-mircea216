package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "order_product")
@EqualsAndHashCode(callSuper = true)
public class Order extends MainEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "product")
    private Product productOrder;
    @ManyToOne
    @JoinColumn(name = "shipped_from")
    private Location location;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    private LocalDate createdAt;
    private String country;
    private String city;
    private String county;
    private String streetAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
}
