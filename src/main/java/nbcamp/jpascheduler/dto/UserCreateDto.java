package nbcamp.jpascheduler.dto;

import lombok.Getter;

@Getter
public class UserCreateDto {
    private String name;
    private String email;
    private String password;
    private String adminToken;
}
