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
public class Attraction extends Timestamp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String location;
	private String name;

	@Column(columnDefinition = "text")
	private String introduce;

	@OneToOne
	private Address address;

	@OneToOne
	private Theme theme;

	@OneToMany(mappedBy = "attraction")
	private List<AttractionImage> attractionImageList = new ArrayList<>();

}
