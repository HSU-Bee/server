package hsu.bee.petra.schedule.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hsu.bee.petra.code.entity.TravelCode;
import hsu.bee.petra.location.entity.Sigungu;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OtherUserScheduleDto {

    private Long scheduleId;

    private String travelCodeName;

    private Double totalDistance;

    private Long views;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long travelPeriod;

    private Sigungu sigungu;
}
