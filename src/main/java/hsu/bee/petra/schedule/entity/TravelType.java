package hsu.bee.petra.schedule.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import hsu.bee.petra.code.entity.TravelCode;
import hsu.bee.petra.common.entity.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "travel_type")
public class TravelType extends Timestamp {

	@EmbeddedId
	private TravelTypeId id;

	@MapsId("scheduleId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@MapsId("TravelCodeId")
	@ManyToOne
	@JoinColumn(name = "code_id")
	private TravelCode travelCode;

	public void changeSchedule(Schedule schedule) {
		if(this.schedule != null) {
			this.schedule.getTravelTypeList().remove(this);
		}
		this.schedule = schedule;
		if(!schedule.getTravelTypeList().contains(this)) {
			schedule.getTravelTypeList().add(this);
		}
	}
}
