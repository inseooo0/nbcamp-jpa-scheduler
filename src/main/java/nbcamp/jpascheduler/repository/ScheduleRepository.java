package nbcamp.jpascheduler.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nbcamp.jpascheduler.domain.Schedule;
import org.springframework.stereotype.Repository;

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

}
