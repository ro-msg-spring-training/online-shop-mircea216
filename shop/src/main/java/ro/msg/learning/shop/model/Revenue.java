package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "revenue")
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
