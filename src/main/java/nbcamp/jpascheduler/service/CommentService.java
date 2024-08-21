package nbcamp.jpascheduler.service;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Comment;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.dto.CommentCreateDto;
import nbcamp.jpascheduler.dto.ScheduleCreateDto;
import nbcamp.jpascheduler.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final ScheduleService scheduleService;
    private final CommentRepository repository;

    @Transactional
    public Comment save(CommentCreateDto dto) {
        Comment comment = new Comment();
        Schedule schedule = scheduleService.findById(dto.getScheduleId());
        comment.setName(dto.getName());
        comment.setSchedule(schedule);
        comment.setContent(dto.getContent());
        return repository.save(comment);
    }
}
