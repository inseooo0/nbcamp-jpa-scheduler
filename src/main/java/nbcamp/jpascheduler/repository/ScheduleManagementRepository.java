package nbcamp.jpascheduler.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nbcamp.jpascheduler.domain.ScheduleManagement;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleManagementRepository {
    @PersistenceContext
    EntityManager em;

    public ScheduleManagement save(ScheduleManagement scheduleManagement) {
        em.persist(scheduleManagement);
        return scheduleManagement;
    }

    public void removeById(Long id) {
        ScheduleManagement scheduleManagement = findById(id);
        em.remove(scheduleManagement);
    }

    public ScheduleManagement findById(Long id) {
        return em.find(ScheduleManagement.class, id);
    }
}
