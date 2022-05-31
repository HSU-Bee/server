package hsu.bee.petra.schedule.service;

import java.math.BigInteger;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import hsu.bee.petra.location.entity.Area;
import hsu.bee.petra.location.entity.Sigungu;
import hsu.bee.petra.location.entity.SigunguId;
import hsu.bee.petra.location.repository.AreaRepository;
import hsu.bee.petra.location.repository.SigunguRepository;
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

import javax.annotation.PostConstruct;
import javax.persistence.Tuple;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

	private final AttractionRepository attractionRepository;
	private final ScheduleRepository scheduleRepository;
	private final PlanRepository planRepository;
	private final StatusRepository statusRepository;
	private final TravelTypeRepository travelTypeRepository;
	private final AreaRepository areaRepository;
	private final SigunguRepository sigunguRepository;

	private static ArrayList<OtherUserScheduleDto> newestOtherUserScheduleDtos = new ArrayList<>();
	private static ArrayList<OtherUserScheduleDto> famousOtherUserScheduleDtos = new ArrayList<>();
	private static ArrayList<OtherUserScheduleDto> longDistanceOtherUserScheduleDtos = new ArrayList<>();
	private static ArrayList<OtherUserScheduleDto> longPeriodOtherUserScheduleDtos = new ArrayList<>();

	public List<ScheduleDto> recommendSchedules(AnswerDto answerDto, User user) {
		List<ScheduleDto> schedulesRecommended = new ArrayList<>();
		return schedulesRecommended;
	}

	@PostConstruct
	public void postConstruct() {
		updateNewestSchedules();
		updateFamousSchedules();
		updateLongestPeriodSchedule();
		updateLongestDistanceSchedule();
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

	public ArrayList<OtherUserScheduleDto> getNewestSchedules(int size, int page) {

		ArrayList<OtherUserScheduleDto> pagedSchedules = new ArrayList<>();

		if(newestOtherUserScheduleDtos.size() < size*page) {

		} else if(newestOtherUserScheduleDtos.size() < (page+1) * size) {
			for (int i = size * page; i < newestOtherUserScheduleDtos.size(); i++) {
				pagedSchedules.add(newestOtherUserScheduleDtos.get(i));
			}
		} else {
			for (int i = size * page; i < (page + 1) * size; i++) {
				pagedSchedules.add(newestOtherUserScheduleDtos.get(i));
			}
		}
		return pagedSchedules;
	}

	// 매 1시간마다 최신순 스케줄 리스트 최신화
	@Scheduled(cron="0 0 0/1 1/1 * ?")
	public void updateNewestSchedules() {

		int page = 0, size = 100;

		PageRequest pageRequest = PageRequest
				.of(page, size)
				.withSort(Sort.Direction.DESC, "createdAt");

		ArrayList<Schedule> schedules = scheduleRepository.findAllByOrderByCreatedAtDesc(pageRequest);
		ArrayList<Long> scheduleIds = new ArrayList<>();

		for(Schedule schedule : schedules) {
			scheduleIds.add(schedule.getId());
		}

		HashMap<Long, Double> calcedTotalDistances = calculateTotalDistance(scheduleIds);
		HashMap<Long, Sigungu> calcedRepresenatativeArea = calculateRepresentativeArea(scheduleIds);
		HashMap<Long, Long> calcedTotalDate = calculateTotalDate(schedules);
		HashMap<Long, String> schedulesTravelCode = getTravelCodeFromSchedule(scheduleIds);

		newestOtherUserScheduleDtos.clear();

		for(Schedule schedule : schedules ) {

			long id = schedule.getId();
			newestOtherUserScheduleDtos.add(
					OtherUserScheduleDto.builder()
							.scheduleId(id)
							.travelCodeName(schedulesTravelCode.get(id))
							.totalDistance(calcedTotalDistances.get(id))
							.views(schedule.getViews())
							.startDate(schedule.getStartDate())
							.endDate(schedule.getEndDate())
							.travelPeriod(calcedTotalDate.get(id))
							.sigungu(calcedRepresenatativeArea.get(id))
							.build()
			);
		}
	}

	public ArrayList<OtherUserScheduleDto> getFamousSchedules(int size, int page) {

		ArrayList<OtherUserScheduleDto> pagedSchedules = new ArrayList<>();

		if(famousOtherUserScheduleDtos.size() < size*page) {

		} else if(famousOtherUserScheduleDtos.size() < (page+1) * size) {
			for (int i = size * page; i < famousOtherUserScheduleDtos.size(); i++) {
				pagedSchedules.add(famousOtherUserScheduleDtos.get(i));
			}
		} else {
			for (int i = size * page; i < (page + 1) * size; i++) {
				pagedSchedules.add(famousOtherUserScheduleDtos.get(i));
			}
		}

		return pagedSchedules;
	}

	//매 1시간마다 최다뷰 스케줄 리스트 최신화
	@Scheduled(cron="0 0 0/1 1/1 * ?")
	public void updateFamousSchedules() {

		// 최근 1000개 중 가장 많이 조회된 100개 Repo에서 가져오기
		int choices = 1000, size = 100;

		ArrayList<Schedule> schedules = scheduleRepository.getFamousSchedule(choices, size);
		ArrayList<Long> scheduleIds = new ArrayList<>();
//		schedules.stream().map(schedule -> scheduleIds.add(schedule.getId()));

		for(Schedule schedule : schedules) {
			scheduleIds.add(schedule.getId());
		}

		HashMap<Long, Double> calcedTotalDistances = calculateTotalDistance(scheduleIds);
		HashMap<Long, Sigungu> calcedRepresenatativeArea = calculateRepresentativeArea(scheduleIds);
		HashMap<Long, Long> calcedTotalDate = calculateTotalDate(schedules);
		HashMap<Long, String> schedulesTravelCode = getTravelCodeFromSchedule(scheduleIds);

		famousOtherUserScheduleDtos.clear();

		for(Schedule schedule : schedules ) {

			long id = schedule.getId();
			famousOtherUserScheduleDtos.add(
					OtherUserScheduleDto.builder()
							.scheduleId(id)
							.travelCodeName(schedulesTravelCode.get(id))
							.totalDistance(calcedTotalDistances.get(id))
							.views(schedule.getViews())
							.startDate(schedule.getStartDate())
							.endDate(schedule.getEndDate())
							.travelPeriod(calcedTotalDate.get(id))
							.sigungu(calcedRepresenatativeArea.get(id))
							.build()
			);
		}
	}

	public ArrayList<OtherUserScheduleDto> getLongestPeriodSchedules(int size, int page) {

		ArrayList<OtherUserScheduleDto> pagedSchedules = new ArrayList<>();

		if(longPeriodOtherUserScheduleDtos.size() < size*page) {

		} else if(longPeriodOtherUserScheduleDtos.size() < (page+1) * size) {
			for (int i = size * page; i < longPeriodOtherUserScheduleDtos.size(); i++) {
				pagedSchedules.add(longPeriodOtherUserScheduleDtos.get(i));
			}
		} else {
			for (int i = size * page; i < (page + 1) * size; i++) {
				pagedSchedules.add(longPeriodOtherUserScheduleDtos.get(i));
			}
		}

		return pagedSchedules;
	}

	// 매 1시간마다 최장 거리 리스트 최신화
	@Scheduled(cron="0 0 0/1 1/1 * ?")
	public void updateLongestPeriodSchedule() {

		int choices = 1000;
		int size = 100;

		ArrayList<Schedule> schedules = scheduleRepository.getSchedulesUsingTimediff(choices, size);
		ArrayList<Long> scheduleIds = new ArrayList<>();

		for(Schedule schedule : schedules) {
			scheduleIds.add(schedule.getId());
		}

		HashMap<Long, Double> calcedTotalDistances = calculateTotalDistance(scheduleIds);
		HashMap<Long, Sigungu> calcedRepresenatativeArea = calculateRepresentativeArea(scheduleIds);
		HashMap<Long, Long> calcedTotalDate = calculateTotalDate(schedules);
		HashMap<Long, String> schedulesTravelCode = getTravelCodeFromSchedule(scheduleIds);

		longPeriodOtherUserScheduleDtos.clear();

		for(Schedule schedule : schedules ) {

			long id = schedule.getId();
			longPeriodOtherUserScheduleDtos.add(
					OtherUserScheduleDto.builder()
							.scheduleId(id)
							.travelCodeName(schedulesTravelCode.get(id))
							.totalDistance(calcedTotalDistances.get(id))
							.views(schedule.getViews())
							.startDate(schedule.getStartDate())
							.endDate(schedule.getEndDate())
							.travelPeriod(calcedTotalDate.get(id))
							.sigungu(calcedRepresenatativeArea.get(id))
							.build()
			);
		}

	}

	public ArrayList<OtherUserScheduleDto> getLongestDistanceSchedules(int size, int page) {

		ArrayList<OtherUserScheduleDto> pagedSchedules = new ArrayList<>();

		if(longDistanceOtherUserScheduleDtos.size() < size*page) {

		} else if(longDistanceOtherUserScheduleDtos.size() < (page+1) * size) {
			for (int i = size * page; i < longDistanceOtherUserScheduleDtos.size(); i++) {
				pagedSchedules.add(longDistanceOtherUserScheduleDtos.get(i));
			}
		} else {
			for (int i = size * page; i < (page + 1) * size; i++) {
				pagedSchedules.add(longDistanceOtherUserScheduleDtos.get(i));
			}
		}

		return pagedSchedules;
	}

	// 매 1시간마다 최장 거리 리스트 최신화
	@Scheduled(cron="0 0 0/1 1/1 * ?")
	public void updateLongestDistanceSchedule() {

		// 최신순으로 1000개 뽑아서 그 중에서 추리기
		int size = 1000;
		PageRequest pageRequest = PageRequest
				.of(0, size)
				.withSort(Sort.Direction.DESC, "createdAt");

		List<Schedule> schedules = scheduleRepository.findAllByOrderByCreatedAtDesc(pageRequest);
		HashMap<Long, Schedule> scheduleMap = new HashMap<>();
//		schedules.stream().map(schedule -> scheduleIds.add(schedule.getId()));

		for(Schedule schedule : schedules) {
			scheduleMap.put(schedule.getId(), schedule);
		}

		ArrayList<Long> scheduleIds = new ArrayList<>(scheduleMap.keySet());

		HashMap<Long, Double> calcedTotalDistances = calculateTotalDistance(scheduleIds);

		ArrayList<Long> sortedList = new ArrayList<>();

		// 1000개 중 100개 정렬
		for (Map.Entry<Long, Double> entry : calcedTotalDistances.entrySet()) {

			int num =0;
			long scheduleId = entry.getKey();
			double totalDistance = entry.getValue();

			if(sortedList.isEmpty()) {
				sortedList.add(scheduleId);
				continue;
			}

			for(int i=0; i<sortedList.size() && i<100; i++) {

				if(calcedTotalDistances.get(sortedList.get(i)) < totalDistance ) {
					sortedList.add(i, scheduleId);
					break;
				}
			}

		}

		for( Long l : sortedList) {
			System.out.println("scheduleId : " + l + " totalDistance : " + calcedTotalDistances.get(l));
		}

//		schedules = scheduleRepository.findByIdIn(sortedList);
		schedules.clear();

		for(Long id : sortedList) {
			schedules.add(scheduleMap.get(id));
		}

		HashMap<Long, Sigungu> calcedRepresenatativeArea = calculateRepresentativeArea(sortedList);
		HashMap<Long, Long> calcedTotalDate = calculateTotalDate(schedules);
		HashMap<Long, String> schedulesTravelCode = getTravelCodeFromSchedule(sortedList);

		longDistanceOtherUserScheduleDtos.clear();

		for(Schedule schedule : schedules ) {

			long id = schedule.getId();
			longDistanceOtherUserScheduleDtos.add(
					OtherUserScheduleDto.builder()
							.scheduleId(id)
							.travelCodeName(schedulesTravelCode.get(id))
							.totalDistance(calcedTotalDistances.get(id))
							.views(schedule.getViews())
							.startDate(schedule.getStartDate())
							.endDate(schedule.getEndDate())
							.travelPeriod(calcedTotalDate.get(id))
							.sigungu(calcedRepresenatativeArea.get(id))
							.build()
			);
		}

		log.info("최신화");
	}

	// 스케줄 하나 당 총 여행거리 계산
	private HashMap<Long, Double> calculateTotalDistance(List<Long> scheduleIds) {

		// Schedule-Plan-Attraction-Address 전부 join해서 (ScheduleId, planId, planOrder, x, y) 전부 끌어오기
		List<Tuple> tupleResult = scheduleRepository.findXY(scheduleIds);

		List<SchedulePlanXYDto> list = tupleResult.stream()
				.map(t -> new SchedulePlanXYDto(
						t.get(0, BigInteger.class).longValue(),
						t.get(1, BigInteger.class).longValue(),
						t.get(2, Integer.class),
						t.get(3, String.class),
						t.get(4, String.class)
				)).collect(Collectors.toList());

		HashMap<Long, Double> totalDistance = new HashMap<>();

		// 스케줄, planOrder 순으로 조회되었다고 가정
		for(int i=0; i<list.size()-1; i++) {
			if(list.get(i).getScheduleId() == list.get(i+1).getScheduleId()) {

				double x1 = Double.parseDouble(list.get(i).getX());
				double y1 = Double.parseDouble(list.get(i).getY());
				double x2 = Double.parseDouble(list.get(i+1).getX());
				double y2 = Double.parseDouble(list.get(i+1).getY());

				long distance = (long) distance(x1,y1,x2,y2,"M");
				System.out.println("scheduleId : " + list.get(i).getScheduleId() + " | origin : " + list.get(i+1).getPlanId() + " - " + list.get(i).getPlanId());
				System.out.println(list.get(i).getPlanOrder() + " - " + list.get(i+1).getPlanOrder() + "distance : " + distance);
				totalDistance.put(list.get(i).getScheduleId(), totalDistance.getOrDefault(list.get(i).getScheduleId(), 0.0) + distance);
			}
		}

		return totalDistance;
	}

	// 스케줄의 대표지역 선정
	private HashMap<Long, Sigungu> calculateRepresentativeArea(List<Long> scheduleIds) {

		HashMap<Long, Sigungu> result = new HashMap<>();

		List<ScheduleSigunguDto> scheduleList = scheduleRepository.getSigunguFromSchedule(scheduleIds);

		HashMap<Long, ScheduleSigunguDto> maxCount = new HashMap<>();

		for (ScheduleSigunguDto scheduleSigunguDto : scheduleList) {
			if (maxCount.containsKey(scheduleSigunguDto.getScheduleId())) {
				if (scheduleSigunguDto.getCount() > maxCount.get(scheduleSigunguDto.getScheduleId()).getCount()) {
					maxCount.put(scheduleSigunguDto.getScheduleId(), scheduleSigunguDto);
				}
			} else {
				maxCount.put(scheduleSigunguDto.getScheduleId(), scheduleSigunguDto);
			}

			HashSet<Integer> areaIdList = new HashSet<>();
			HashSet<SigunguId> sigunguIdList = new HashSet<>();

			for (ScheduleSigunguDto ssd : maxCount.values()) {
				areaIdList.add(ssd.getArea());
				sigunguIdList.add(new SigunguId(ssd.getSigungu(), ssd.getArea()));
			}

			List<Area> areaList = areaRepository.findByIdIn(new ArrayList<>(areaIdList));
			List<Sigungu> sigunguList = sigunguRepository.findByIdIn(new ArrayList<>(sigunguIdList));

			for (ScheduleSigunguDto ssd : maxCount.values()) {

				Area a = null;
				for (int i = 0; i < areaList.size(); i++) {
					if (areaList.get(i).getId() == ssd.getArea()) {
						a = areaList.get(i);
						break;
					}
				}

				SigunguId si = new SigunguId(ssd.getSigungu(), ssd.getArea());
				for (int i = 0; i < sigunguList.size(); i++) {
					if (sigunguList.get(i).getId().equals(si)) {
						result.put(ssd.getScheduleId(), new Sigungu(si, a, sigunguList.get(i).getName()));
						break;
					}
				}
			}
		}

		return result;
	}

	// 스케줄의 총 여행일 계산
	private HashMap<Long, Long> calculateTotalDate(List<Schedule> schedules) {

		HashMap<Long, Long> scheduleTotalDates = new HashMap<>();
		for(Schedule schedule : schedules) {
			scheduleTotalDates.put(schedule.getId(), ChronoUnit.DAYS.between(schedule.getStartDate(), schedule.getEndDate()));
		}

		return scheduleTotalDates;
	}

	// 스케줄의 여행 타입 가져오기
	private HashMap<Long, String> getTravelCodeFromSchedule(List<Long> scheduleIds) {

		HashMap<Long, String> result = new HashMap<>();

		List<TravelTypeDto> TravelTypeList = travelTypeRepository.findTravelTypeBySchedule(scheduleIds);

		for (TravelTypeDto travelTypeDto : TravelTypeList) {
			result.put(travelTypeDto.getScheduleId(), travelTypeDto.getTravelCodeName());
		}
		return result;
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
