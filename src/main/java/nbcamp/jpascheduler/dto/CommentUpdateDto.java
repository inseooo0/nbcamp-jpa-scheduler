package nbcamp.jpascheduler.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentUpdateDto {
    private Long scheduleId;
    private Long userId;
    private String content;
}
