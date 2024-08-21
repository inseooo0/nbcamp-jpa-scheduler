package nbcamp.jpascheduler.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ScheduleResponseDto {
    private Long id;
    private String name;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
