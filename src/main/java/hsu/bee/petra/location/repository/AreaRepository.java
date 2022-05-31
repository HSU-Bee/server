package hsu.bee.petra.location.repository;

import hsu.bee.petra.location.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Integer> {

    public List<Area> findByIdIn(List<Integer> areaId);
}
