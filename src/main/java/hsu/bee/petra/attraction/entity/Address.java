package hsu.bee.petra.attraction.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import hsu.bee.petra.common.entity.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Address extends Timestamp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "text")
	private String x;

	@Column(columnDefinition = "text")
	private String y;

	@Column(columnDefinition = "text")
	private String address;

	@Column(columnDefinition = "text")
	private String detail;

	private String zipcode;

	@OneToMany
	private List<Attraction> attractionList = new ArrayList<>();


}
