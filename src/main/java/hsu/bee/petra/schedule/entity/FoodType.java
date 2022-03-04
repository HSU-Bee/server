package hsu.bee.petra.schedule.entity;

import javax.persistence.*;

import hsu.bee.petra.code.entity.FoodCode;
import hsu.bee.petra.common.entity.Timestamp;

@Entity
@Table(name = "food_type")
public class FoodType extends Timestamp {
	// schedule, food_code 복합키 매핑

    @MapsId("codeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id")
    private FoodCode foodCode;

    @MapsId("scheduleId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @EmbeddedId
    private FoodTypeId foodTypeId;
}
