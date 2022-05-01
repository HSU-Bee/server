package hsu.bee.petra.schedule.repository;

import hsu.bee.petra.schedule.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

}
