package nbcamp.jpascheduler.dto;

import lombok.Getter;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private List<UserResponseDto> users = new ArrayList<>();
    private String title;
    private String content;
    private String weather;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public ScheduleResponseDto(Long id, String title, String content,
                               String weather, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.weather = weather;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public static ScheduleResponseDto of(Schedule schedule){
        ScheduleResponseDto dto = new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContent(),
                schedule.getWeather(), schedule.getCreateAt(), schedule.getUpdateAt());
        // user dto 넣기
        List<User> users = schedule.findUsers();
        for (User user : users) {
            dto.getUsers().add(UserResponseDto.of(user));
        }
        return dto;
    }
}
