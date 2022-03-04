package hsu.bee.petra.schedule.entity;

import javax.persistence.*;

import hsu.bee.petra.code.entity.TravelCode;
import hsu.bee.petra.common.entity.Timestamp;

@Entity
@Table(name = "travel_type")
public class TravelType extends Timestamp {
	// schedule, travel_code 복합키
    @MapsId("scheduleId")
    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;

    @MapsId("codeId")
    @ManyToOne
    @JoinColumn(name="code_id")
    private TravelCode travelCode;

    @EmbeddedId
    private TravelTypeId travelTypeId;

}
