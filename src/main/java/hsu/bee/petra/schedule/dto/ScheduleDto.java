package hsu.bee.petra.schedule.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hsu.bee.petra.code.entity.Status;
import hsu.bee.petra.schedule.entity.FoodType;
import hsu.bee.petra.schedule.entity.TravelType;
import hsu.bee.petra.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDto {
	private Long id;
	private String title;
	private int adult;
	private int child;
	private LocalDate startDate;
	private LocalDate endDate;
	private User user;
	private Status status;
	private List<PlanDto> planList = new ArrayList<>();
	private List<FoodType> foodTypeList = new ArrayList<>();
	private List<TravelType> travelTypeList = new ArrayList<>();
}
