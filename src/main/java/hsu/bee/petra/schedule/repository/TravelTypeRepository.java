package hsu.bee.petra.schedule.repository;

import hsu.bee.petra.schedule.dto.TravelTypeDto;
import hsu.bee.petra.schedule.entity.Schedule;
import hsu.bee.petra.schedule.entity.TravelType;
import hsu.bee.petra.schedule.entity.TravelTypeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TravelTypeRepository extends JpaRepository<TravelType, TravelTypeId> {

    Optional<TravelType> findBySchedule(Schedule schedule);

    @Query(value="select tt.schedule_id as scheduleId, tc.code as travelCodeName\n" +
            "from travel_code tc, travel_type tt\n" +
            "where tt.code_id = tc.id and tt.schedule_id in :scheduleList", nativeQuery = true)
    List<TravelTypeDto> findTravelTypeBySchedule(@Param("scheduleList") List<Long> scheduleList);
}
