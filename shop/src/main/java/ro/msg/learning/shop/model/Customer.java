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
public class Customer extends MainEntity<Integer> {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
}
