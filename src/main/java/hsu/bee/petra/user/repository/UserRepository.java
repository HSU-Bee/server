package hsu.bee.petra.user.repository;

import hsu.bee.petra.code.entity.TravelCode;
import hsu.bee.petra.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    public User findOne(String id) {

        return em.find(User.class, id);
    }

    public void saveTravelCode(String id, TravelCode travelCode) {

        User user = findOne(id);
        user.setTravelCode(travelCode);
    }

}
