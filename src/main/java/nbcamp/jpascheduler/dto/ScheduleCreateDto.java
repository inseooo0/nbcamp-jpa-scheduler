package nbcamp.jpascheduler.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ScheduleCreateDto {
    private List<Long> userIds;
    private String title;
    private String content;
}
