package nbcamp.jpascheduler.dto;

import lombok.Getter;

@Getter
public class CommentCreateDto {
    private Long scheduleId;
    private Long userId;
    private String content;
}
