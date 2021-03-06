package hsu.bee.petra.schedule.repository;

import hsu.bee.petra.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hsu.bee.petra.schedule.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Long countByUser(User user);
}
