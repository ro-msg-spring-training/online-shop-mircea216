package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
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
}
