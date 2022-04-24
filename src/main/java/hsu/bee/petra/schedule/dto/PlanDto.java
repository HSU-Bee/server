package hsu.bee.petra.schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanDto {
	private long id;
	private String memo;
	private int order;
	private String startDate;
	private String endDate;
	private long scheduleId;   //ScheduleDto schedule;
	private long attractionId; //AttractionDto attraction;
}
