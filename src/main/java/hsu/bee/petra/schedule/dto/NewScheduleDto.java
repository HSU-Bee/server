package hsu.bee.petra.schedule.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewScheduleDto {
	private String title;
	private int adult;
	private int child;
	private String startDate;
	private String endDate;
	private long userId;
	private List<PlanDto> plans;
}
