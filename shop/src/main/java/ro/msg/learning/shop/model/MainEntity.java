package ro.msg.learning.shop.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
@Getter
@Setter

@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MainEntity<ID extends Serializable> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;
}
