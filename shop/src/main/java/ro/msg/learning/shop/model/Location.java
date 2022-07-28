package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Location extends MainEntity<Integer> {
    private String name;
    private String country;
    private String city;
    private String county;
    private String streetAddress;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Revenue> revenues;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Stock> stocks;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Order> orders;
}
