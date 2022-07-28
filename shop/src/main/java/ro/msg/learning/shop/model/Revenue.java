package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)

public class Revenue extends MainEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;
    private LocalDateTime date;
    private Double sum;
}
