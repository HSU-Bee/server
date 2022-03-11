package hsu.bee.petra.schedule.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import hsu.bee.petra.code.entity.FoodCode;
import hsu.bee.petra.common.entity.Timestamp;

@Entity
@Table(name = "food_type")
public class FoodType extends Timestamp {

	@EmbeddedId
	private FoodTypeId id;

	@MapsId("scheduleId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@MapsId("foodCodeId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "code_id")
	private FoodCode foodCode;
}
