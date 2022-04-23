package hsu.bee.petra.code.repository;

import hsu.bee.petra.code.entity.TravelCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class TravelCodeRepository {

    private final EntityManager em;

    public TravelCode findByCode(String code) {
        return em.createQuery("select m from TravelCode m where m.code = :code", TravelCode.class)
                .setParameter("code", code)
                .getSingleResult();
    }
}
