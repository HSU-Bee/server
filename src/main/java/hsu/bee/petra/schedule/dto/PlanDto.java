package hsu.bee.petra.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hsu.bee.petra.schedule.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanDto {

    private Long planId;
    private String memo;
    private Integer order;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Long ScheduleId;
    private String attractionName;

    public PlanDto(Plan plan) {
        this.planId = plan.getId();
        this.memo = plan.getMemo();
        this.order = plan.getOrder();
        this.startDate = plan.getStartDate();
        this.endDate = plan.getEndDate();
        this.ScheduleId = plan.getSchedule().getId();
        this.attractionName = plan.getAttraction().getName();
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

}
