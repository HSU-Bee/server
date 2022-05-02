package hsu.bee.petra.code.repository;

import hsu.bee.petra.code.entity.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {
    Status findByName(String name);
}
