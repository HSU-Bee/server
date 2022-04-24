package hsu.bee.petra.schedule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import hsu.bee.petra.schedule.dto.AnswerDto;
import hsu.bee.petra.schedule.dto.NewScheduleDto;
import hsu.bee.petra.schedule.dto.ScheduleDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {

	public List<ScheduleDto> recommendSchedules(AnswerDto answerDto) {
		List<ScheduleDto> schedulesRecommended = new ArrayList<>();
		return schedulesRecommended;
	}

	public void createSchedule(NewScheduleDto newScheduleDto) {
		// user 유효성 검사
		// date 형식 검사
	}
}
