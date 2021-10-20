package ir.bigz.rediscache.domain.item;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemView implements Serializable {

    private static final long serialVersionUID=128L;

    private Long id;
    private Long quantity;
    private String name;
    private Timestamp createDate;
}
