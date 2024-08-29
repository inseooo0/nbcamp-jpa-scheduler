package nbcamp.jpascheduler.dto;

import lombok.Getter;

@Getter
public class CommentUpdateDto {
    private Long scheduleId;
    private Long userId;
    private String content;
}
