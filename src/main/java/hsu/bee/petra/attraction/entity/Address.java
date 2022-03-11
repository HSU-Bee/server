package hsu.bee.petra.attraction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import hsu.bee.petra.common.entity.Timestamp;

@Entity
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
	private String detailAddress;

	@Column(columnDefinition = "text")
	private String buildingName;
}
