package ro.msg.learning.shop.model;

import lombok.*;
import ro.msg.learning.shop.dto.OrderDto;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Customer extends MainEntity<Integer> {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer(String firstName, String lastName, String username, String password,
                    String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public Customer(String firstName, String lastName, String username, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.emailAddress = emailAddress;
    }

    public Customer(OrderDto orderDto) {
        this.id = orderDto.getCustomer().getId();
        this.firstName = orderDto.getCustomer().getFirstName();
        this.lastName = orderDto.getCustomer().getLastName();
        this.emailAddress = orderDto.getCustomer().getEmailAddress();
        this.username = orderDto.getCustomer().getUsername();
    }
}
