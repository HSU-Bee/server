package hsu.bee.petra.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hsu.bee.petra.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
