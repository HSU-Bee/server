package hsu.bee.petra.schedule.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hsu.bee.petra.response.Response;
import hsu.bee.petra.response.ResponseCode;
import hsu.bee.petra.response.ResponseMessage;
import hsu.bee.petra.schedule.dto.AnswerDto;
import hsu.bee.petra.schedule.dto.NewScheduleDto;
import hsu.bee.petra.schedule.dto.ScheduleDto;
import hsu.bee.petra.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

	private static final Response success = new Response();
	private final ScheduleService scheduleService;

	@PostMapping("/schedule/answer")
	public Response<List<ScheduleDto>> createSchedule(@RequestBody AnswerDto answerDto) {
		List<ScheduleDto> scheduleRecommended = scheduleService.recommendSchedules(answerDto);
		return new Response<>(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, scheduleRecommended);
	}

	@PostMapping("/schedule/new")
	public Response createSchedule(@RequestBody NewScheduleDto newScheduleDto) {
		scheduleService.createSchedule(newScheduleDto);
		return success;
	}
}
