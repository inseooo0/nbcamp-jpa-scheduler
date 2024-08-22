package nbcamp.jpascheduler.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ScheduleResponseDto {
    private Long id;
    private List<UserResponseDto> users = new ArrayList<>();
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
