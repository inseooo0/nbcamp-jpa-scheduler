package nbcamp.jpascheduler.dto;

import lombok.Getter;
import lombok.Setter;
import nbcamp.jpascheduler.domain.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private Long scheduleId;
    private String content;
    @Setter
    private UserResponseDto user;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public CommentResponseDto(Long id, Long scheduleId, String content,
                              LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public static CommentResponseDto of(Comment comment) {
        CommentResponseDto dto = new CommentResponseDto(comment.getId(), comment.getSchedule().getId(), comment.getContent(),
                comment.getCreateAt(), comment.getUpdateAt());
        dto.setUser(UserResponseDto.of(comment.getUser()));
        return dto;
    }
}
