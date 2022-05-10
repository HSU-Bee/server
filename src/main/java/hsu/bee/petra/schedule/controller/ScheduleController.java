package hsu.bee.petra.schedule.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hsu.bee.petra.common.annotation.AuthenticatedUser;
import hsu.bee.petra.response.Response;
import hsu.bee.petra.response.ResponseCode;
import hsu.bee.petra.response.ResponseMessage;
import hsu.bee.petra.schedule.dto.AnswerDto;
import hsu.bee.petra.schedule.dto.BasicScheduleInfoDto;
import hsu.bee.petra.schedule.dto.CopyScheduleDto;
import hsu.bee.petra.schedule.dto.NewScheduleDto;
import hsu.bee.petra.schedule.dto.ScheduleDto;
import hsu.bee.petra.schedule.service.PlanService;
import hsu.bee.petra.schedule.service.ScheduleService;
import hsu.bee.petra.user.entity.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

	private static final Response success = new Response();
	private final ScheduleService scheduleService;
	private final PlanService planService;

	@PostMapping("/schedules/plans/copy")
	public Response<BasicScheduleInfoDto> copyPlanToSchedule(@Valid @RequestBody CopyScheduleDto copyScheduleDto) {

		if(copyScheduleDto.getPlanIdList().length == 0)
			throw new IllegalArgumentException("배열이 비어있습니다.");

		List<Long> planIdList =
			Stream.of(copyScheduleDto.getPlanIdList()).collect(Collectors.toList());

		Long newScheduleId = copyScheduleDto.getNewScheduleId() != null ? copyScheduleDto.getNewScheduleId() : 0L;

		newScheduleId = planService.copyAndSavePlan(
			copyScheduleDto.getUserId(), copyScheduleDto.getScheduleId(), newScheduleId, planIdList
		);

		BasicScheduleInfoDto scd = new BasicScheduleInfoDto();
		scd.setScheduleId(newScheduleId);
		return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, scd);
	}

	@PostMapping("/schedule/answer")
	public Response<List<ScheduleDto>> createSchedule(@RequestBody AnswerDto answerDto, @AuthenticatedUser User user) {
		List<ScheduleDto> scheduleRecommended = scheduleService.recommendSchedules(answerDto, user);
		return new Response<>(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, scheduleRecommended);
	}

	@PostMapping("/schedule/new")
	public Response createSchedule(@RequestBody NewScheduleDto newScheduleDto, @AuthenticatedUser User user) {
		scheduleService.createSchedule(newScheduleDto, user);
		return success;
	}

	@GetMapping("/schedule/{scheduleId}")
	public Response<ScheduleDto> getSchedule(@PathVariable("scheduleId") Long scheduleId) {
		ScheduleDto schedule = scheduleService.getSchedule(scheduleId);
		return new Response<>(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, schedule);
	}
}
