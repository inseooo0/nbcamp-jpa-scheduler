package nbcamp.jpascheduler.service;

import jakarta.transaction.Transactional;
import nbcamp.jpascheduler.domain.Comment;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.CommentCreateDto;
import nbcamp.jpascheduler.dto.CommentUpdateDto;
import nbcamp.jpascheduler.dto.ScheduleCreateDto;
import nbcamp.jpascheduler.dto.UserCreateDto;
import nbcamp.jpascheduler.exception.ApiException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduleServiceTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ScheduleService scheduleService;

    @Test
    @Transactional
    public void save() {

        ScheduleCreateDto scheduleCreateDto = new ScheduleCreateDto();
        scheduleCreateDto.setTitle("title");
        scheduleCreateDto.setContent("content");
        scheduleCreateDto.setUserIds(List.of(1L, 2L, 3L));

        Schedule saved = scheduleService.save(scheduleCreateDto);
        Schedule findSchedule = scheduleService.findById(saved.getId());

        Assertions.assertThat(saved).isEqualTo(findSchedule);
        Assertions.assertThat(findSchedule.findUsers()).contains(userService.findById(1L));
        Assertions.assertThat(findSchedule.findUsers()).contains(userService.findById(2L));
        Assertions.assertThat(findSchedule.findUsers()).contains(userService.findById(3L));
    }

    @Test
    @Transactional
    public void saveError() {
        ScheduleCreateDto scheduleCreateDto = new ScheduleCreateDto();
        scheduleCreateDto.setTitle("title");
        scheduleCreateDto.setContent("content");
        scheduleCreateDto.setUserIds(List.of(0L)); // 존재하지 않는 user id

        Assertions.assertThatThrownBy(() -> scheduleService.save(scheduleCreateDto))
                .isInstanceOf(ApiException.class);
    }
}