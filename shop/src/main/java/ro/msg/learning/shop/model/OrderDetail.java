package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrderDetail extends MainEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "order_product")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;
    private Integer quantity;
}
