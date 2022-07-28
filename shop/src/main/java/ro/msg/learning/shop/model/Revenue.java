package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)

public class Revenue extends MainEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;
    private LocalDateTime date;
    private Double sum;
}
