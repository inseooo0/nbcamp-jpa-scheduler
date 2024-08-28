package nbcamp.jpascheduler.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.JpaSchedulerApplication;
import nbcamp.jpascheduler.config.AppConfig;
import nbcamp.jpascheduler.domain.Comment;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.CommentCreateDto;
import nbcamp.jpascheduler.dto.ScheduleCreateDto;
import nbcamp.jpascheduler.dto.UserCreateDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ScheduleService scheduleService;

    @Test
    @Transactional
    public void save() {

        User user = saveUser();
        Schedule schedule = saveSchedule();

        CommentCreateDto commentCreateDto = new CommentCreateDto();
        commentCreateDto.setContent("test comment content");
        commentCreateDto.setUserId(user.getId());
        commentCreateDto.setScheduleId(schedule.getId());

        Comment saved = commentService.save(commentCreateDto);

        Comment findComment  = commentService.findById(saved.getId());
        Assertions.assertThat(saved).isEqualTo(findComment);
    }

    @Transactional
    public User saveUser() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName("UserA");
        userCreateDto.setEmail("userA@email.com");
        userCreateDto.setPassword("0000");
        return userService.save(userCreateDto);
    }

    @Transactional
    public Schedule saveSchedule() {
        ScheduleCreateDto scheduleCreateDto = new ScheduleCreateDto();
        scheduleCreateDto.setUserIds(List.of(1L));
        scheduleCreateDto.setContent("schedule content");
        scheduleCreateDto.setTitle("test schedule");
        return scheduleService.save(scheduleCreateDto);
    }
}