package hsu.bee.petra.schedule.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import hsu.bee.petra.code.entity.TravelCode;
import hsu.bee.petra.common.entity.Timestamp;

@Entity
@Table(name = "travel_type")
public class TravelType extends Timestamp {
	// schedule, travel_code 복합키
	@EmbeddedId
	private TravelTypeId id;

	@MapsId("scheduleId")
	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@MapsId("TravelCodeId")
	@ManyToOne
	@JoinColumn(name = "code_id")
	private TravelCode travelCode;
}
