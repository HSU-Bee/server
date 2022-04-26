package hsu.bee.petra.schedule.service;

import hsu.bee.petra.code.entity.Status;
import hsu.bee.petra.code.repository.StatusRepository;
import hsu.bee.petra.schedule.dto.PlanDto;
import hsu.bee.petra.schedule.dto.ScheduleDto;
import hsu.bee.petra.schedule.entity.Plan;
import hsu.bee.petra.schedule.entity.Schedule;
import hsu.bee.petra.schedule.repository.PlanRepository;
import hsu.bee.petra.schedule.repository.ScheduleRepository;
import hsu.bee.petra.user.entity.User;
import hsu.bee.petra.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class PlanService {
    private final PlanRepository planRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final ScheduleService scheduleService;

    public ScheduleDto copyAndSavePlan(String userId, Long oldId, Long newId, List<Long> orderIdList) throws IllegalArgumentException {

        Schedule oldSchedule;
        Schedule newSchedule;
        User user = userRepository.findOne(userId);

        if(user == null) {
            throw new IllegalArgumentException("잘못된 userId 입니다");
        }

        // oldSchedule 존재 예외 처리
        oldSchedule = scheduleRepository.findOne(oldId);
        if(oldSchedule == null)
            throw new IllegalArgumentException("존재하지 않는 oldSchedule 입니다");


        // newSchedule 존재 예외 처리
        if(newId == 0L) {
            Status status = statusRepository.findOne("여행전");
            newSchedule = scheduleService.createSchedule(user, status);
        } else {
            newSchedule = scheduleRepository.findOne(newId);
            if(newSchedule == null)
                throw new IllegalArgumentException("존재하지 않는 newSchedule 입니다");
        }

        List<Plan> planList = copyPlan(oldSchedule, newSchedule, orderIdList);
        List<PlanDto> planDtoList = planToDto(planList);
        return new ScheduleDto(oldSchedule.getUser().getId(), newSchedule.getId(), planDtoList);
    }

    public List<Plan> copyPlan(Schedule oldOne, Schedule newOne, List<Long> orderIdList) throws IllegalArgumentException {

        List<Plan> copiedPlanList = new ArrayList<>();

        //원본 스케줄 찾아서 해당 order Plan 전부 가져옴
        List<Plan> planList = planRepository.findByScheduleAndOrder(oldOne, orderIdList);

        if(planList.size() != orderIdList.size())
            throw new IllegalArgumentException("요청한 planId 중 존재하지 않는 Id가 존재합니다");

        int maxOrder = planRepository.findMaxOrder(newOne);


        for (Plan plan : planList) {
            Plan newPlan = plan.copyPlan();
            newPlan.changeSchedule(newOne);
            newPlan.changeOrder(++maxOrder);
            planRepository.save(newPlan);
            copiedPlanList.add(newPlan);
        }

        return copiedPlanList;
    }

    public List<PlanDto> planToDto(List<Plan> planList) {

        List<PlanDto> planDtoList = new ArrayList<>();
        for (Plan plan : planList) {
            //plan의 schedule은 proxy 상태
            planDtoList.add(new PlanDto(plan));
        }

        return planDtoList;
    }

    public void savePlan(Plan plan) {
        planRepository.save(plan);
    }

    public List<Plan> findPlanBySchedule(Schedule schedule) {
        return planRepository.findBySchedule(schedule);
    }

}
