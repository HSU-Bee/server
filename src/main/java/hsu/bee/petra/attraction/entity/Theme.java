package hsu.bee.petra.attraction.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import hsu.bee.petra.common.entity.Timestamp;

@Entity
public class Theme extends Timestamp {

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
}