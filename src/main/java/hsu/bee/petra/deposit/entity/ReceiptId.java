package hsu.bee.petra.deposit.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class ReceiptId implements Serializable {

	@Column(name = "deposit_id")
	private Long depositId;

	@Column(name = "image_id")
	private Long imageId;
}
