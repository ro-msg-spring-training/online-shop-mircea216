package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class ProductCategory extends MainEntity<Integer> {
    private String name;
    private String description;
}
