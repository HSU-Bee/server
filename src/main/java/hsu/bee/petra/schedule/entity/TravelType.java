package hsu.bee.petra.schedule.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import hsu.bee.petra.common.entity.Timestamp;

@Entity
@Table(name = "travel_type")
public class TravelType extends Timestamp {
	// schedule, travel_code 복합키
}
