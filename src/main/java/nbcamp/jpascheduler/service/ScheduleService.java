package nbcamp.jpascheduler.service;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.domain.ScheduleManagement;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.ScheduleCreateDto;
import nbcamp.jpascheduler.dto.ScheduleUpdateDto;
import nbcamp.jpascheduler.repository.ScheduleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository repository;
    private final UserService userService;
    private final ScheduleManagementService scheduleManagementService;

    @Transactional
    public Schedule save(ScheduleCreateDto dto) {
        Schedule schedule = new Schedule();
        List<Long> userIds = dto.getUserIds();
        for (Long userId : userIds) {
            User user = userService.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException();
            }
            schedule.getManagementList().add(scheduleManagementService.save(schedule, user));
        }
        schedule.setTitle(dto.getTitle());
        schedule.setContent(dto.getContent());
        return repository.save(schedule);
    }

    public Schedule findById(Long id) {
        Optional<Schedule> checkId = repository.findById(id);
        if (checkId.isEmpty()) throw new IllegalArgumentException();

        return checkId.get();
    }

    public List<Schedule> findAll(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("update_at").descending());
        return repository.findAll(pageRequest).getContent();
    }

    @Transactional
    public Schedule update(Long id, ScheduleUpdateDto dto) {
        Optional<Schedule> checkId = repository.findById(id);
        if (checkId.isEmpty()) throw new IllegalArgumentException();

        Schedule schedule = checkId.get();
        schedule.setTitle(dto.getTitle());
        schedule.setContent(dto.getContent());
        List<Long> userIds = dto.getUserIds();
        List<ScheduleManagement> currentManagements = schedule.getManagementList();
        List<Long> currentUserIds = schedule.findUserIds();
        List<ScheduleManagement> removeManagements = new ArrayList<>();

        for (ScheduleManagement sm : currentManagements) {
            if (!userIds.contains(sm.getUser().getId())) {
                scheduleManagementService.removeById(sm.getId());
                removeManagements.add(sm);
            }
        }

        for (Long userId : userIds) {
            if (!currentUserIds.contains(userId)) {
                User user = userService.findById(userId);
                if (user == null) {
                    throw new IllegalArgumentException();
                }
                ScheduleManagement newManagement = scheduleManagementService.save(schedule, user);
                currentManagements.add(newManagement);
            }
        }
        currentManagements.removeAll(removeManagements);
        return schedule;
    }

    @Transactional
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
