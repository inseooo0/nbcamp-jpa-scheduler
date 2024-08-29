package nbcamp.jpascheduler.dto;

import lombok.Getter;
import nbcamp.jpascheduler.domain.User;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String userRole;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public UserResponseDto(Long id, String name, String email,
                           String userRole, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userRole = userRole;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(),
                user.getUserRole().name(), user.getCreateAt(), user.getUpdateAt());
    }
}
