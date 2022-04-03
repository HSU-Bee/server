package hsu.bee.petra.schedule.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import hsu.bee.petra.response.Response;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

	private static final Response success = new Response();

	@PostMapping("/schedule")
	public Response createSchedule() {

		return success;
	}
}
