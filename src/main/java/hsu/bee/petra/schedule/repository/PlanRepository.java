package hsu.bee.petra.schedule.repository;

import hsu.bee.petra.schedule.entity.Plan;
import hsu.bee.petra.schedule.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PlanRepository {

    private final EntityManager em;

    public List<Plan> findBySchedule(Schedule schedule) {
        return em.createQuery("select p from Plan p where p.schedule = :schedule order by p.order asc", Plan.class)
                .setParameter("schedule", schedule)
                .getResultList();
    }

    public List<Plan> findByScheduleAndOrder(Schedule schedule, List<Long> planIdList) {
        return em.createQuery("select p from Plan p where p.schedule = :schedule and p.id in :orders " +
                        "order by p.order asc", Plan.class)
                .setParameter("schedule", schedule)
                .setParameter("orders", planIdList)
                .getResultList();
    }

    public int findMaxOrder(Schedule schedule) {

        int maxOrder;

        try {
            maxOrder = em.createQuery("select max(p.order) from Plan p where p.schedule = :schedule", Integer.class)
                    //(Integer) em.createQuery("select Max(p.order) from Plan p where p.schedule = :schedule")
                    .setParameter("schedule", schedule)
                    .getResultList().stream().findFirst().orElseGet(() -> 0);
        } catch (NullPointerException e) {
            maxOrder = 0;
        }

        return maxOrder;
    }

    public void save(Plan plan) {
        em.persist(plan);
    }

}