package hsu.bee.petra.schedule.repository;

import hsu.bee.petra.schedule.entity.Schedule;
import hsu.bee.petra.schedule.entity.TravelType;
import hsu.bee.petra.schedule.entity.TravelTypeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelTypeRepository extends JpaRepository<TravelType, TravelTypeId> {

    Optional<TravelType> findBySchedule(Schedule schedule);
}
