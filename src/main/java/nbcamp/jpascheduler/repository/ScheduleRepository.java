package nbcamp.jpascheduler.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nbcamp.jpascheduler.domain.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleRepository {

    @PersistenceContext EntityManager em;

    public Schedule save(Schedule schedule) {
        em.persist(schedule);
        return schedule;
    }

    public Schedule findById(Long id) {
        return em.find(Schedule.class, id);
    }

    public List<Schedule> findAll(int offset, int limit) {
        return em.createQuery("select s from Schedule s order by updateAt desc", Schedule.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
