package ro.msg.learning.shop.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Stock extends MainEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product productStock;

    private Integer quantity;
}
