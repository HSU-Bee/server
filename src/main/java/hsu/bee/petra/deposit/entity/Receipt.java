package hsu.bee.petra.deposit.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import hsu.bee.petra.image.entity.Image;

@Entity
public class Receipt {

	@EmbeddedId
	private ReceiptId id;

	@MapsId("depositId")
	@ManyToOne
	@JoinColumn(name = "deposit_id")
	private Deposit deposit;

	@MapsId("imageId")
	@ManyToOne
	@JoinColumn(name = "image_id")
	private Image image;
}
