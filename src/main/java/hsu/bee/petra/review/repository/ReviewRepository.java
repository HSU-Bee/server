package hsu.bee.petra.review.repository;

import hsu.bee.petra.review.entity.Review;
import hsu.bee.petra.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Long countByUser(User user);

}
