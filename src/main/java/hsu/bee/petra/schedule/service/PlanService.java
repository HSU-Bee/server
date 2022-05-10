package hsu.bee.petra.schedule.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hsu.bee.petra.attraction.entity.Attraction;
import hsu.bee.petra.attraction.repository.AttractionRepository;
import hsu.bee.petra.schedule.dto.ScheduleDto;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hsu.bee.petra.code.repository.StatusRepository;
import hsu.bee.petra.schedule.dto.PlanDto;
import hsu.bee.petra.schedule.entity.Plan;
import hsu.bee.petra.schedule.entity.Schedule;
import hsu.bee.petra.schedule.repository.PlanRepository;
import hsu.bee.petra.schedule.repository.ScheduleRepository;
import hsu.bee.petra.user.entity.User;
import hsu.bee.petra.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final AttractionRepository attractionRepository;

    public Long copyAndSavePlan(String userId, Long oldId, Long newId, List<Long> orderIdList) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("잘못된 userId 입니다"));
        Schedule oldSchedule = scheduleRepository.findById(oldId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 oldSchedule 입니다"));
        Schedule newSchedule;

        // newSchedule 존재 예외 처리
        if(newId == 0L) {
            Schedule nsc = Schedule.builder()
                    .title("빈 스케줄")
                    .adult(1)
                    .child(0)
                    .startDate(LocalDate.now())
                    .endDate(LocalDate.now())
                    .user(user)
                    .status(statusRepository.findByName("여행전"))
                    .build();
            newSchedule = scheduleRepository.save(nsc);
        } else {
            newSchedule = scheduleRepository.findById(newId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 newSchedule 입니다"));
        }

        List<Plan> planList = planRepository.findByScheduleAndIdIn(oldSchedule, orderIdList);

        if(planList.size() != orderIdList.size())
            throw new IllegalArgumentException("요청한 planId 중 존재하지 않는 Id가 존재합니다");

        Integer maxOrder = planRepository.findMaxOrder(newSchedule);

        if(maxOrder == null)
            maxOrder = 0;

        for (Plan plan : planList) {
            Plan newPlan = Plan.builder()
                    .memo(plan.getMemo())
                    .order(++maxOrder)
                    .startDate(LocalDate.now())
                    .endDate(LocalDate.now())
                    .attraction(plan.getAttraction())
                    .build();

            newPlan.changeSchedule(newSchedule);
            planRepository.save(newPlan);
        }

        return newSchedule.getId();
    }

    public List<PlanDto> planToDto(List<Plan> planList) {

        List<PlanDto> planDtoList = new ArrayList<>();
        for (Plan plan : planList) {
            //plan의 schedule은 proxy 상태
            planDtoList.add(new PlanDto(plan));
        }

        return planDtoList;
    }

    public Long createPlan(PlanDto planDto) {

        Schedule schedule = scheduleRepository.findById(planDto.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Schedule 입니다."));

        Attraction attraction;
        try {
            attraction = attractionRepository.findByName(planDto.getAttractionName()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Attraction 입니다."));
        } catch(IncorrectResultSizeDataAccessException e) {
            throw new IllegalArgumentException("중복되는 Attraction이 존재합니다.");
        }

        int order = planRepository.findMaxOrder(schedule);

        Plan newPlan = Plan.builder()
                .memo(planDto.getMemo())
                .order(++order)
                .startDate(planDto.getStartDate())
                .endDate(planDto.getEndDate())
                .attraction(attraction)
                .build();

        newPlan.changeSchedule(schedule);
        newPlan = planRepository.save(newPlan);

        return newPlan.getSchedule().getId();
    }

}
