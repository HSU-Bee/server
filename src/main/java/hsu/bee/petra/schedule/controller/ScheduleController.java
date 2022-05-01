package hsu.bee.petra.schedule.controller;

import hsu.bee.petra.response.Response;
import hsu.bee.petra.response.ResponseCode;
import hsu.bee.petra.response.ResponseMessage;
import hsu.bee.petra.schedule.dto.CopyScheduleDto;
import hsu.bee.petra.schedule.dto.ScheduleDto;
import hsu.bee.petra.schedule.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
public class ScheduleController {

    private final PlanService planService;

    @PostMapping("/schedules/plans/copy")
    public Response<ScheduleDto> copyPlanToSchedule(@Valid @RequestBody CopyScheduleDto copyScheduleDto) {

        if(copyScheduleDto.getPlanIdList().length == 0)
            throw new IllegalArgumentException("배열이 비어있습니다.");

        List<Long> planIdList =
                Stream.of(copyScheduleDto.getPlanIdList()).collect(Collectors.toList());

        Long newScheduleId = copyScheduleDto.getNewScheduleId() != null ? copyScheduleDto.getNewScheduleId() : 0L;

        newScheduleId = planService.copyAndSavePlan(
                copyScheduleDto.getUserId(), copyScheduleDto.getScheduleId(), newScheduleId, planIdList
        );

        ScheduleDto scd = new ScheduleDto();
        scd.setScheduleId(newScheduleId);
        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, scd);
    }

}
