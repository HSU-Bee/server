package hsu.bee.petra.deposit.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class ReceiptId implements Serializable {

	@Column(name = "deposit_id")
	private Long depositId;

	@Column(name = "image_id")
	private Long imageId;
}
