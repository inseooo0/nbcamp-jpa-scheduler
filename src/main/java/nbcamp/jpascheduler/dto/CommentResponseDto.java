package nbcamp.jpascheduler.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CommentResponseDto {
    private Long id;
    private Long scheduleId;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
