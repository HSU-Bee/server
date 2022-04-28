package hsu.bee.petra.schedule.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CopyScheduleDto {

    private String userId;
    private Long[] planIdList;
    private Long scheduleId;
    private Long newScheduleId;

}
