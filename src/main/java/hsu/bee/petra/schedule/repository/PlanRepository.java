package hsu.bee.petra.schedule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hsu.bee.petra.schedule.entity.Plan;
import hsu.bee.petra.schedule.entity.Schedule;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findBySchedule(Schedule schedule);

    List<Plan> findByScheduleAndIdIn(Schedule schedule, List<Long> planIdList);

    @Query("SELECT max(p.order) FROM Plan p WHERE p.schedule = :schedule")
    Integer findMaxOrder(@Param("schedule")Schedule schedule);

}
