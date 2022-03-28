package hsu.bee.petra.schedule.repository;

import hsu.bee.petra.code.entity.TravelCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class TravelTypeRepsitory {

    EntityManager em;

    public TravelCode save() {

    }
}
