package hsu.bee.petra.schedule.service;

import java.math.BigInteger;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import hsu.bee.petra.schedule.dto.*;
import hsu.bee.petra.schedule.entity.TravelType;
import hsu.bee.petra.schedule.repository.TravelTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hsu.bee.petra.attraction.entity.Attraction;
import hsu.bee.petra.attraction.repository.AttractionRepository;
import hsu.bee.petra.code.entity.Status;
import hsu.bee.petra.code.repository.StatusRepository;
import hsu.bee.petra.schedule.entity.Plan;
import hsu.bee.petra.schedule.entity.Schedule;
import hsu.bee.petra.schedule.repository.PlanRepository;
import hsu.bee.petra.schedule.repository.ScheduleRepository;
import hsu.bee.petra.user.entity.User;
import hsu.bee.petra.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Tuple;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

	private final UserRepository userRepository;
	private final AttractionRepository attractionRepository;
	private final ScheduleRepository scheduleRepository;
	private final PlanRepository planRepository;
	private final StatusRepository statusRepository;
	private final TravelTypeRepository travelTypeRepository;
	private static ArrayList<OtherUserScheduleDto> longDistanceScheduleDtoList = new ArrayList<>();

	public List<ScheduleDto> recommendSchedules(AnswerDto answerDto, User user) {
		List<ScheduleDto> schedulesRecommended = new ArrayList<>();
		return schedulesRecommended;
	}

	@Transactional
	public void createSchedule(NewScheduleDto newScheduleDto, User user) {
		// date 형식 검사 생략
		Optional<Status> optionalStatus = statusRepository.findById(1L);
		Schedule schedule = new Schedule(newScheduleDto, user, optionalStatus.get());
		scheduleRepository.save(schedule);

		Plan plan;
		for (PlanDto planDto : newScheduleDto.getPlans()) {
			Optional<Attraction> optionalAttraction = attractionRepository.findById(planDto.getAttraction().getId());
			plan = new Plan(planDto, optionalAttraction.get()); // attraction 유효성 검사 생략
			plan.changeSchedule(schedule);
			planRepository.save(plan);
		}
	}

	@Transactional
	public ScheduleDto getSchedule(Long scheduleId) {
		Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
		if(optionalSchedule.isEmpty()) {
			throw new IllegalArgumentException(
				"유효하지 않은 scheduleId 입니다. (입력된 scheduleId : " + scheduleId + ")");
		}
		Schedule schedule = optionalSchedule.get();
		System.out.println(schedule.getPlanList().get(0).getMemo());
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
		System.out.println(planList.get(0).getMemo());
		List<PlanDto> planDtoList = new ArrayList<>();
		for(Plan plan : planList) {
			planDtoList.add(PlanDto.convertPlanDto(plan));
		}
		return planDtoList;
	}

    public ArrayList<OtherUserScheduleDto> getFamousSchedule() {

		int range = 1000;
		int size = 10;
		ArrayList<Schedule> scheduleList = scheduleRepository.getFamousSchedule(range, size);

		ArrayList<OtherUserScheduleDto> result = new ArrayList<>();
		for (Schedule schedule : scheduleList) {
			OtherUserScheduleDto temp =
					OtherUserScheduleDto.builder()
							.scheduleId(schedule.getId())
							.views(schedule.getViews())
							.startDate(schedule.getStartDate())
							.endDate(schedule.getEndDate())
							.travelPeriod(ChronoUnit.DAYS.between(schedule.getStartDate(), schedule.getEndDate()))
							.build();

			TravelType travelCode = travelTypeRepository.findBySchedule(schedule).orElseThrow(() -> new IllegalArgumentException("해당 스케줄의 TravelCode가 존재하지 않습니다."));
			temp.setTravelCode(travelCode.getTravelCode());
			result.add(temp);
		}

		return result;
    }

	public ArrayList<OtherUserScheduleDto> getNewestSchedule(Pageable pageable) {

		ArrayList<OtherUserScheduleDto> result = new ArrayList<>();
		ArrayList<Schedule> scheduleList = scheduleRepository.findAllByOrderByCreatedAtDesc(pageable);

		for (Schedule schedule : scheduleList) {
			OtherUserScheduleDto temp =
					OtherUserScheduleDto.builder()
							.scheduleId(schedule.getId())
							.views(schedule.getViews())
							.startDate(schedule.getStartDate())
							.endDate(schedule.getEndDate())
							.travelPeriod(ChronoUnit.DAYS.between(schedule.getStartDate(), schedule.getEndDate()))
							.build();

			TravelType travelCode = travelTypeRepository.findBySchedule(schedule).orElseThrow(() -> new IllegalArgumentException("해당 스케줄의 TravelCode가 존재하지 않습니다."));
			temp.setTravelCode(travelCode.getTravelCode());
			result.add(temp);
		}

		return result;
	}

	public ArrayList<OtherUserScheduleDto> getLongestTimeSchedule() {

		int limit = 1000;
		int size = 10;
		ArrayList<OtherUserScheduleDto> result = new ArrayList<>();
		ArrayList<Schedule> scheduleList = new ArrayList<>();

		// 가장 최근생성된 limit개 에서 가장 여행 기간 긴 스케쥴을 몇 page의 몇 size만큼 가져오기
		scheduleList = scheduleRepository.getSchedulesUsingTimediff(limit, size);

		for (Schedule schedule : scheduleList) {
			OtherUserScheduleDto temp =
					OtherUserScheduleDto.builder()
							.scheduleId(schedule.getId())
							.views(schedule.getViews())
							.startDate(schedule.getStartDate())
							.endDate(schedule.getEndDate())
							.travelPeriod(ChronoUnit.DAYS.between(schedule.getStartDate(), schedule.getEndDate()))
							.build();

			TravelType travelCode = travelTypeRepository.findBySchedule(schedule).orElseThrow(() -> new IllegalArgumentException("해당 스케줄의 TravelCode가 존재하지 않습니다."));
			temp.setTravelCode(travelCode.getTravelCode());
			result.add(temp);
		}

		return result;
	}

	public ArrayList<OtherUserScheduleDto> getLongestDistanceSchedule(int size, int page) {

		return longDistanceScheduleDtoList;
	}

	// 매 30분마다 최장 거리 리스트 최신화
	@Scheduled(cron="0 0/3 * 1/1 * ?")
	protected void updateLongDistanceSchedule() {

		// 최신순으로 1000개 뽑아서 그 중에서 추리기
		int size = 1000;
		PageRequest pageRequest = PageRequest
				.of(0, size)
				.withSort(Sort.Direction.DESC, "createdAt");

		Page<Schedule> scheduleList = scheduleRepository.findAll(pageRequest);
//		ArrayList<Long> scheduleIdList = new ArrayList<>();
		HashMap<Long, Schedule> scheduleIdList = new HashMap<>();
		scheduleList.map((schedule) -> scheduleIdList.put(schedule.getId(), schedule));

		// Schedule-Plan-Attraction-Address 전부 join해서 (ScheduleId, planId, planOrder, x, y) 전부 끌어오기
		List<Tuple> tupleResult = scheduleRepository.findXY(new ArrayList<>(scheduleIdList.keySet()));

		List<SchedulePlanXYDto> schedulePlanXYDto = tupleResult.stream()
				.map(t -> new SchedulePlanXYDto(
						t.get(0, BigInteger.class).longValue(),
						t.get(1, BigInteger.class).longValue(),
						t.get(2, Integer.class),
						t.get(3, String.class),
						t.get(4, String.class)
				)).collect(Collectors.toList());

		// 100개의 <스케줄 id, 거리 총합> 저장
		HashMap<Long, Double> totalDistance = new HashMap<>();

		// 스케줄, planOrder 순으로 조회되었다고 가정
		// Math.round(pie*100)/100.0
		for(int i=0; i<schedulePlanXYDto.size()-1; i++) {
			if(schedulePlanXYDto.get(i).getScheduleId() == schedulePlanXYDto.get(i+1).getScheduleId()) {
				double x1 = Double.parseDouble(schedulePlanXYDto.get(i).getX());
				double y1 = Double.parseDouble(schedulePlanXYDto.get(i).getY());
				double x2 = Double.parseDouble(schedulePlanXYDto.get(i+1).getX());
				double y2 = Double.parseDouble(schedulePlanXYDto.get(i+1).getY());
				long distance = (long) distance(x1,y1,x2,y2,"M");
				System.out.println("scheduleId : " + schedulePlanXYDto.get(i).getScheduleId() + " | origin : " + schedulePlanXYDto.get(i+1).getPlanId() + " - " + schedulePlanXYDto.get(i).getPlanId());
				System.out.println(schedulePlanXYDto.get(i).getPlanOrder() + " - " + schedulePlanXYDto.get(i+1).getPlanOrder() + "distance : " + distance);
				totalDistance.put(schedulePlanXYDto.get(i).getScheduleId(), totalDistance.getOrDefault(schedulePlanXYDto.get(i).getScheduleId(), 0.0) + distance);
			}

		}

		ArrayList<Long> sortedList = new ArrayList<>();

		for (Map.Entry<Long, Double> entry : totalDistance.entrySet()) {
			if(sortedList.isEmpty()) {
				sortedList.add(entry.getKey());
				continue;
			}

			int num =0;

			while(entry.getValue() < totalDistance.get(sortedList.get(num)) && num < sortedList.size()) {
				if(num > 10)
					break;
				num++;
			}
			if(num < 10) {
				sortedList.add(num, entry.getKey());
			}
		}
//		longDistanceSchedule.clear();
		longDistanceScheduleDtoList.clear();
		for (Long a : sortedList) {
			Schedule s = scheduleIdList.get(a);
			OtherUserScheduleDto ousd = OtherUserScheduleDto.builder()
					.scheduleId(s.getId())
					.totalDistance(totalDistance.get(s.getId())/(double)1000)
					.startDate(s.getStartDate())
					.endDate(s.getEndDate())
					.views(s.getViews())
					.build();

			TravelType travelCode = travelTypeRepository.findBySchedule(s).orElseThrow(() -> new IllegalArgumentException("해당 스케줄의 TravelCode가 존재하지 않습니다."));
			ousd.setTravelCode(travelCode.getTravelCode());
			ousd.setTravelPeriod(ChronoUnit.DAYS.between(ousd.getStartDate(), ousd.getEndDate()));
//			longDistanceSchedule.put(a, totalDistance.get(a));
			longDistanceScheduleDtoList.add(ousd);
			System.out.println("scheduleId : " + s.getId() + " : " + ousd.getScheduleId() + " distance : " + ousd.getTotalDistance());
		}

		log.info("최신화");
	}

	// 출처 - https://www.geodatasource.com/developers/java
	private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			if (unit.equals("K")) {
				dist = dist * 1.609344;
			} else if (unit.equals("N")) {
				dist = dist * 0.8684;
			} else if (unit.equals("M")) {
				dist = dist * 1609.344;
			}
			return (dist);
		}
	}
}
