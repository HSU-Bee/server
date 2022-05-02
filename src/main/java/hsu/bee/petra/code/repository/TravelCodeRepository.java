package hsu.bee.petra.code.repository;

import hsu.bee.petra.code.entity.TravelCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface TravelCodeRepository extends JpaRepository<TravelCode, Long> {

    TravelCode findByCode(String code);
}
