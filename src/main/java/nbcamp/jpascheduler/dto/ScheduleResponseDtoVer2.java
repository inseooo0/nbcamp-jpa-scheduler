package nbcamp.jpascheduler.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ScheduleResponseDtoVer2 {
    private Long id;
    private String title;
    private String content;
    private String weather;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
