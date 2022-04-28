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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
public class ScheduleController {

    private final PlanService planService;

    // 기존 schedule의 plan들을 복사 -> 새 schedule의 plan에 저장
    @PostMapping("/schedules/copy-create-schedule")
    public Response<ScheduleDto> copyPlanToNewSchedule(@RequestBody CopyScheduleDto copyScheduleDto) {

        if(copyScheduleDto.getUserId() == null) {
            throw new IllegalArgumentException("userId가 없음");
        }

        if(copyScheduleDto.getPlanIdList() == null) {
            throw new IllegalArgumentException("PlanId 배열이 없음");
        }

        if(copyScheduleDto.getScheduleId() == null) {
            throw new IllegalArgumentException("Schedule Id가 없음");
        }

        List<Long> planIdList =
                Stream.of(copyScheduleDto.getPlanIdList()).collect(Collectors.toList());

        ScheduleDto scd = planService.copyAndSavePlan(
                copyScheduleDto.getUserId(), copyScheduleDto.getScheduleId(), 0L, planIdList
        );

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, scd);
    }

    // 기존 schedule의 plan을 복사해서 기존 schedule의 뒤에 복사
    @PostMapping("/schedules/copy-paste-schedule")
    public Response<ScheduleDto> copyPlanToOldSchedule(@RequestBody CopyScheduleDto copyScheduleDto) {

        if(copyScheduleDto.getUserId() == null) {
            throw new IllegalArgumentException("userId가 없음");
        }

        if(copyScheduleDto.getPlanIdList() == null) {
            throw new IllegalArgumentException("PlanId 배열이 없음");
        }

        if(copyScheduleDto.getScheduleId() == null) {
            throw new IllegalArgumentException("ScheduleId가 없음");
        }

        if(copyScheduleDto.getNewScheduleId() == null) {
            throw new IllegalArgumentException("NewScheduleId가 없음");
        }

        List<Long> planIdList =
                Stream.of(copyScheduleDto.getPlanIdList()).collect(Collectors.toList());

        ScheduleDto scd = planService.copyAndSavePlan(
                copyScheduleDto.getUserId(), copyScheduleDto.getScheduleId(),
                copyScheduleDto.getNewScheduleId(), planIdList
        );

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, scd);
    }
}
