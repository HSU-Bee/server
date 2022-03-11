package hsu.bee.petra.deposit.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import hsu.bee.petra.image.entity.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Receipt {

	@EmbeddedId
	private ReceiptId id;

	@MapsId("depositId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deposit_id")
	private Deposit deposit;

	@MapsId("imageId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_id")
	private Image image;

	public void changeDeposit(Deposit deposit) {
		if(this.deposit != null) {
			this.deposit.getReceiptList().remove(this);
		}
		this.deposit = deposit;
		if(!deposit.getReceiptList().contains(this)) {
			deposit.getReceiptList().add(this);
		}
	}
}
