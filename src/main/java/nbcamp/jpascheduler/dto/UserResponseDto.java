package nbcamp.jpascheduler.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String userRole;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
