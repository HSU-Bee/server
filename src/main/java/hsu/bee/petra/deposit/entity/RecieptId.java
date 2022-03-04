package hsu.bee.petra.deposit.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
public class RecieptId implements Serializable {

    @Column(name="deposit_id")
    private Long depositId;

    @Column(name="image_id")
    private Long imageId;
}