package hsu.bee.petra.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hsu.bee.petra.schedule.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
