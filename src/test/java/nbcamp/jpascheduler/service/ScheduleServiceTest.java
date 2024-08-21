package nbcamp.jpascheduler.service;

import jakarta.transaction.Transactional;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.dto.ScheduleCreateDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@SpringBootTest
class ScheduleServiceTest {

    @Autowired
    ScheduleService scheduleService;

    ModelMapper mapper = new ModelMapper();

    @Test
    @Transactional
    public void testSchedule() {
        ScheduleCreateDto scheduleCreateDto = new ScheduleCreateDto();
        scheduleCreateDto.setTitle("schedule1");
        scheduleCreateDto.setName("userA");
        scheduleCreateDto.setContent("scheduleContent");
        Schedule saved = scheduleService.save(scheduleCreateDto);

        Schedule findSchedule = scheduleService.findById(saved.getId());
        Assertions.assertThat(saved).isEqualTo(findSchedule);
    }
}