package hsu.bee.petra.attraction.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import hsu.bee.petra.common.entity.Timestamp;
// import hsu.bee.petra.locatiion.entity.Sigungu;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Attraction extends Timestamp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "sigungu_id")
	// private Sigungu sigungu;

	@OneToOne
	private Theme theme;

	public void changeAddress(Address address) {
		if(this.address != null) {
			this.address.getAttractionList().remove(this);
		}

		this.address = address;
		if(!address.getAttractionList().contains(this)) {
			address.getAttractionList().add(this);
		}
	}
}
