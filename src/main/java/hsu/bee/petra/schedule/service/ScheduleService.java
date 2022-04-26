package hsu.bee.petra.schedule.service;

import hsu.bee.petra.code.entity.Status;
import hsu.bee.petra.schedule.entity.Schedule;
import hsu.bee.petra.schedule.repository.ScheduleRepository;
import hsu.bee.petra.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule createSchedule(User user, Status status) {

        Schedule schedule = new Schedule(user, status);
        scheduleRepository.save(schedule);

        return schedule;
    }

    public Schedule findOne(long scheduleId) {
        return scheduleRepository.findOne(scheduleId);
    }

}
