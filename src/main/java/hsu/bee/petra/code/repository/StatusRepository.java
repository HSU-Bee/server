package hsu.bee.petra.code.repository;

import hsu.bee.petra.code.entity.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class StatusRepository {

    private final EntityManager em;

    public Status find(Long id) {
        return em.find(Status.class, id);
    }

    public Status findOne(String name) {
        return em.createQuery("select s from Status s where s.name = :name", Status.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
