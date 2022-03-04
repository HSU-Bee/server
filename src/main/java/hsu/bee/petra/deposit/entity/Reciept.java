package hsu.bee.petra.deposit.entity;

import javax.persistence.*;

import hsu.bee.petra.common.entity.Timestamp;
import hsu.bee.petra.image.entity.Image;

@Entity
public class Reciept {
	// deposit, image 복합키 매핑

    @MapsId("depositId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="deposit_id")
    private Deposit deposit;

    @MapsId("imageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="image_id")
    private Image image;

    @EmbeddedId
    private RecieptId recieptId;
}
