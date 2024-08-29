package nbcamp.jpascheduler.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleCreateDto {
    private List<Long> userIds;
    private String title;
    private String content;
}
