package hsu.bee.petra.schedule.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class FoodTypeId implements Serializable {

	@Column(name = "schedule_id")
	private Long scheduleId;

	@Column(name = "code_id")
	private Long foodCodeId;
}