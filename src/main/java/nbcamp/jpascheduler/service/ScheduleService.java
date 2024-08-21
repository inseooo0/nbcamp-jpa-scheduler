package nbcamp.jpascheduler.service;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.dto.ScheduleCreateDto;
import nbcamp.jpascheduler.dto.ScheduleUpdateDto;
import nbcamp.jpascheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository repository;

    @Transactional
    public Schedule save(ScheduleCreateDto dto) {
        Schedule schedule = new Schedule();
        schedule.setName(dto.getName());
        schedule.setTitle(dto.getTitle());
        schedule.setContent(dto.getContent());
        return repository.save(schedule);
    }

    public Schedule findById(Long id) {
        return repository.findById(id);
    }

    public List<Schedule> findAll(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return repository.findAll(offset, pageSize);
    }

    @Transactional
    public Schedule update(Long id, ScheduleUpdateDto dto) {
        Schedule schedule = repository.findById(id);
        schedule.setName(dto.getName());
        schedule.setTitle(dto.getTitle());
        schedule.setContent(dto.getContent());
        return schedule;
    }

    @Transactional
    public void removeById(Long id) {
        repository.removeById(id);
    }
}
