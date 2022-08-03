package ro.msg.learning.shop.model;

import lombok.*;
import ro.msg.learning.shop.dto.OrderDto;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Location extends MainEntity<Integer> {
    private String name;
    private String country;
    private String city;
    private String county;
    private String streetAddress;

    @OneToMany(mappedBy = "location")
    private List<Revenue> revenues;
    @OneToMany(mappedBy = "location")
    private List<Stock> stocks;
    @OneToMany(mappedBy = "location")
    private List<Order> orders;

    public Location(String name, String country, String city, String county, String streetAddress) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.county = county;
        this.streetAddress = streetAddress;
    }

    public Location(OrderDto orderDto) {
        this.name = orderDto.getName();
        this.id = orderDto.getLocationId();
        this.country = orderDto.getCountry();
        this.city = orderDto.getCity();
        this.county = orderDto.getCounty();
        this.streetAddress = orderDto.getStreetAddress();
    }
}
