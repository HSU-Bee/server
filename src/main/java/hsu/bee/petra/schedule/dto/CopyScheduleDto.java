package hsu.bee.petra.schedule.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CopyScheduleDto {

    private String userId;
    private long[] planIdList;
    private long scheduleId;
    private long newScheduleId;

    public CopyScheduleDto() {
        newScheduleId = 0L;
    }

}
