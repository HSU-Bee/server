package hsu.bee.petra.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulePlanXYDto {

    Long scheduleId;
    Long planId;
    Integer planOrder;
    String x;
    String y;

    public SchedulePlanXYDto(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

}
