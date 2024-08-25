package nbcamp.jpascheduler.service;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.domain.ScheduleManagement;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.repository.ScheduleManagementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleManagementService {
    private final ScheduleManagementRepository repository;

    @Transactional
    public ScheduleManagement save(Schedule schedule, User user) {
        ScheduleManagement scheduleManagement = new ScheduleManagement();
        scheduleManagement.setSchedule(schedule);
        scheduleManagement.setUser(user);
        return repository.save(scheduleManagement);
    }

    @Transactional
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
