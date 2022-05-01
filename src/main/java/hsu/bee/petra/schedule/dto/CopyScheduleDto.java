package hsu.bee.petra.schedule.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CopyScheduleDto {

    @NotNull(message = "userId가 필요합니다")
    private String userId;
    @NotNull(message = "plan 배열이 비어있습니다")
    private Long[] planIdList;
    @NotNull(message = "복사할 scheduleId가 필요합니다.")
    private Long scheduleId;
    private Long newScheduleId;

}
