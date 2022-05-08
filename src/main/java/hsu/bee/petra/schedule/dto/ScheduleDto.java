package hsu.bee.petra.schedule.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

import hsu.bee.petra.code.entity.Status;
import hsu.bee.petra.schedule.entity.FoodType;
import hsu.bee.petra.schedule.entity.TravelType;
import hsu.bee.petra.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ScheduleDto {

	private Long id;
	private String title;
	private int adult;
	private int child;
	private LocalDate startDate;
	private LocalDate endDate;
	private User user;
	private Status status;

	@Builder.Default
	private List<PlanDto> planList = new ArrayList<>();

	@Builder.Default
	private List<FoodType> foodTypeList = new ArrayList<>();

	@Builder.Default
	private List<TravelType> travelTypeList = new ArrayList<>();

}
