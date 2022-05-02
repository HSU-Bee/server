package hsu.bee.petra.attraction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import hsu.bee.petra.location.entity.Sigungu;
import hsu.bee.petra.time.Timestamp;
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

	@Column(columnDefinition = "text")
	private String imageUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "sigungu_id"),
		@JoinColumn(name = "area_id")
	})
	private Sigungu sigungu;

	@OneToOne
	@JoinColumn(name = "theme_id")
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
