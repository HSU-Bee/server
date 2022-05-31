package hsu.bee.petra.location.repository;

import hsu.bee.petra.location.entity.Sigungu;
import hsu.bee.petra.location.entity.SigunguId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SigunguRepository extends JpaRepository<Sigungu, SigunguId> {

    public List<Sigungu> findByIdIn(List<SigunguId> sigunguIdList);
}
