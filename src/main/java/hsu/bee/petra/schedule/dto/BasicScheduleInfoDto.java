package hsu.bee.petra.schedule.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BasicScheduleInfoDto {

	private String userId;
	private Long scheduleId;
	private List<PlanDto> planList;

	public BasicScheduleInfoDto(String userId, Long scheduleId, List<PlanDto> planList) {
		this.userId = userId;
		this.scheduleId = scheduleId;
		this.planList = planList;
	}
}
