package hsu.bee.petra.schedule.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class FoodTypeId implements Serializable {

	@Column(name = "schedule_id")
	private Long scheduleId;

	@Column(name = "code_id")
	private Long foodCodeId;
}
