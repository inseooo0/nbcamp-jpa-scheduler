package nbcamp.jpascheduler.repository;

import nbcamp.jpascheduler.domain.ScheduleManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleManagementRepository extends JpaRepository<ScheduleManagement, Long> {
}
