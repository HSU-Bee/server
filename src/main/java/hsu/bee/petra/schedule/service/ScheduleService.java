package hsu.bee.petra.schedule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hsu.bee.petra.attraction.entity.Attraction;
import hsu.bee.petra.attraction.repository.AttractionRepository;
import hsu.bee.petra.schedule.dto.AnswerDto;
import hsu.bee.petra.schedule.dto.NewScheduleDto;
import hsu.bee.petra.schedule.dto.PlanDto;
import hsu.bee.petra.schedule.dto.ScheduleDto;
import hsu.bee.petra.schedule.entity.Plan;
import hsu.bee.petra.schedule.entity.Schedule;
import hsu.bee.petra.schedule.repository.PlanRepository;
import hsu.bee.petra.schedule.repository.ScheduleRepository;
import hsu.bee.petra.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

	private final UserRepository userRepository;
	private final AttractionRepository attractionRepository;
	private final ScheduleRepository scheduleRepository;
	private final PlanRepository planRepository;

	public List<ScheduleDto> recommendSchedules(AnswerDto answerDto, User user) {
		List<ScheduleDto> schedulesRecommended = new ArrayList<>();
		return schedulesRecommended;
	}

	public void createSchedule(NewScheduleDto newScheduleDto, User user) {
		// date 형식 검사 생략
		Schedule schedule = new Schedule(newScheduleDto, user);
		scheduleRepository.save(schedule);

		Plan plan;
		for (PlanDto planDto : newScheduleDto.getPlans()) {
			Optional<Attraction> optionalAttraction = attractionRepository.findById(planDto.getAttraction().getId());
			plan = new Plan(planDto, optionalAttraction.get()); // attraction 유효성 검사 생략
			plan.changeSchedule(schedule);
			planRepository.save(plan);
		}
	}

	public ScheduleDto getSchedule(Long scheduleId) {
		Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
		if(optionalSchedule.isEmpty()) {
			IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
				"유효하지 않은 scheduleId 입니다. (입력된 scheduleId : " + scheduleId + ")");
			log.error(
				"ScheduleService :: getSchedule(Long id) "
					+ "- 유효하지 않은 scheduleId 입니다. (입력된 scheduleId : " + scheduleId + ")",
				illegalArgumentException);
		}
		Schedule schedule = optionalSchedule.get();
		List<PlanDto> planDtoList = changePlanListToPlanDtoList(schedule.getPlanList());

		return ScheduleDto.builder()
			.id(schedule.getId())
			.title(schedule.getTitle())
			.startDate(schedule.getStartDate())
			.endDate(schedule.getEndDate())
			.planList(planDtoList)
			.build();
	}

	private List<PlanDto> changePlanListToPlanDtoList(List<Plan> planList) {
		List<PlanDto> planDtoList = new ArrayList<>();
		for(Plan plan : planList) {
			planDtoList.add(PlanDto.convertPlanDto(plan));
		}
		return planDtoList;
	}
}
