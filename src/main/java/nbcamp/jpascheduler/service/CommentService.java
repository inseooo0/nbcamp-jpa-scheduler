package nbcamp.jpascheduler.service;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Comment;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.CommentCreateDto;
import nbcamp.jpascheduler.dto.CommentUpdateDto;
import nbcamp.jpascheduler.exception.ApiException;
import nbcamp.jpascheduler.exception.CommonErrorCode;
import nbcamp.jpascheduler.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final ScheduleService scheduleService;
    private final UserService userService;
    private final CommentRepository repository;

    @Transactional
    public Comment save(CommentCreateDto dto) {
        Schedule schedule = scheduleService.findById(dto.getScheduleId());
        User user = userService.findById(dto.getUserId());
        Comment comment = new Comment(schedule, user, dto.getContent());
        return repository.save(comment);
    }

    public Comment findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException(CommonErrorCode.INVALID_PARAMETER));
    }

    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Comment update(Long id, CommentUpdateDto dto) {

        Comment comment = findById(id);
        Schedule schedule = scheduleService.findById(dto.getScheduleId());
        User user = userService.findById(dto.getUserId());

        comment.update(schedule, user, dto.getContent());
        return comment;
    }

    @Transactional
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
