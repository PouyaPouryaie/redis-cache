package ir.bigz.rediscache.domain.item;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "Item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Item implements Serializable {

    private static final long serialVersionUID=127L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Integer version;
    private Timestamp createDate;
    @Column(unique = true)
    private String name;
    private Long quantity;

    @PrePersist
    private void insertTime(){
        this.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
    }
}
