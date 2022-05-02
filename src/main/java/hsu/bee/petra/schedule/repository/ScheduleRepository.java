package hsu.bee.petra.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hsu.bee.petra.schedule.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
