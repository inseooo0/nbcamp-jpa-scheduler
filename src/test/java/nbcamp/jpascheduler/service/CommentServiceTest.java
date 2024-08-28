package nbcamp.jpascheduler.service;

import jakarta.transaction.Transactional;
import nbcamp.jpascheduler.domain.Comment;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.CommentCreateDto;
import nbcamp.jpascheduler.dto.CommentUpdateDto;
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

        User user = saveUser("UserA", "UserA@email.com");
        Schedule schedule = saveSchedule("schedule title", "schedule content");

        CommentCreateDto commentCreateDto = new CommentCreateDto();
        commentCreateDto.setContent("test comment content");
        commentCreateDto.setUserId(user.getId());
        commentCreateDto.setScheduleId(schedule.getId());

        Comment saved = commentService.save(commentCreateDto);

        Comment findComment  = commentService.findById(saved.getId());
        Assertions.assertThat(saved).isEqualTo(findComment);
    }

    @Test
    @Transactional
    public void update() {

        User userA = saveUser("UserA", "UserA@email.com");
        Schedule schedule = saveSchedule("schedule title", "schedule content");

        CommentCreateDto commentCreateDto = new CommentCreateDto();
        commentCreateDto.setContent("test comment content");
        commentCreateDto.setUserId(userA.getId());
        commentCreateDto.setScheduleId(schedule.getId());

        Comment saved = commentService.save(commentCreateDto);

        User userB = saveUser("UserB", "UserB@email.com");
        Schedule newSchedule = saveSchedule("new schedule", "content");

        CommentUpdateDto commentUpdateDto = new CommentUpdateDto();
        commentUpdateDto.setContent("update Content");
        commentUpdateDto.setUserId(userB.getId());
        commentUpdateDto.setScheduleId(newSchedule.getId());

        Comment updated = commentService.update(saved.getId(), commentUpdateDto);
        Comment findComment = commentService.findById(updated.getId());

        Assertions.assertThat(updated).isEqualTo(findComment);
        Assertions.assertThat(updated.getUser()).isEqualTo(userB);
        Assertions.assertThat(updated.getSchedule()).isEqualTo(newSchedule);
        Assertions.assertThat(updated.getContent()).isEqualTo("update Content");
    }

    @Transactional
    public User saveUser(String username, String email) {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName(username);
        userCreateDto.setEmail(email);
        userCreateDto.setPassword("0000");
        return userService.save(userCreateDto);
    }

    @Transactional
    public Schedule saveSchedule(String title, String content) {
        ScheduleCreateDto scheduleCreateDto = new ScheduleCreateDto();
        scheduleCreateDto.setUserIds(List.of(1L));
        scheduleCreateDto.setContent(content);
        scheduleCreateDto.setTitle(title);
        return scheduleService.save(scheduleCreateDto);
    }
}