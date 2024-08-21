package nbcamp.jpascheduler.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ScheduleCreateDto {
    private String name;
    private String title;
    private String content;
}
