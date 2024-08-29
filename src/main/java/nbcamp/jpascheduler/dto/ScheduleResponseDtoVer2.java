package nbcamp.jpascheduler.dto;

import lombok.Getter;
import nbcamp.jpascheduler.domain.Schedule;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDtoVer2 {
    private Long id;
    private String title;
    private String content;
    private String weather;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public ScheduleResponseDtoVer2(Long id, String title, String content,
                               String weather, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.weather = weather;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public static ScheduleResponseDtoVer2 of(Schedule schedule){
        return new ScheduleResponseDtoVer2(schedule.getId(), schedule.getTitle(), schedule.getContent(),
                schedule.getWeather(), schedule.getCreateAt(), schedule.getUpdateAt());
    }
}
