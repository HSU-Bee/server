package hsu.bee.petra.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

    private String userId;
    private Long scheduleId;
    private List<PlanDto> planList;
}
