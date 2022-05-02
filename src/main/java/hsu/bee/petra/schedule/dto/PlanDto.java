package hsu.bee.petra.schedule.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import hsu.bee.petra.attraction.dto.AttractionDto;
import hsu.bee.petra.schedule.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
public class PlanDto {
	private long id;
	private String memo;
	private int order;
	private String startDate;
	private String endDate;
	private long scheduleId;   //ScheduleDto schedule;
	private AttractionDto attraction;

	public static PlanDto convertPlanDto(Plan plan) {
		return PlanDto.builder()
			.id(plan.getId())
			.memo(plan.getMemo())
			.order(plan.getOrder())
			.startDate(String.valueOf(plan.getStartDate()))
			.endDate(String.valueOf(plan.getEndDate()))
			.attraction(AttractionDto.convertAttractionDto(plan.getAttraction()))
			.build();
	}
 }
