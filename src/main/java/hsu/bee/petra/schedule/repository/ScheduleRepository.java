package hsu.bee.petra.schedule.repository;

import hsu.bee.petra.schedule.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class ScheduleRepository {


    private final EntityManager em;

    public void save(Schedule schedule) {
        em.persist(schedule);
    }

    public Schedule findOne(long id) {
        return em.find(Schedule.class, id);
    }

}
