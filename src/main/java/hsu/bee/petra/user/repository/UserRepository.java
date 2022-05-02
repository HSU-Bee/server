package hsu.bee.petra.user.repository;

import hsu.bee.petra.code.entity.TravelCode;
import hsu.bee.petra.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
