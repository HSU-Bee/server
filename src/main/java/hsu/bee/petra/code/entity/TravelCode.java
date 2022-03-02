package hsu.bee.petra.code.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import hsu.bee.petra.common.entity.Timestamp;

@Entity
@Table(name = "travel_code")
public class TravelCode extends Timestamp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;
}
