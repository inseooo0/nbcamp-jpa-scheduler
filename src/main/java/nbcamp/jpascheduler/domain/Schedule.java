package nbcamp.jpascheduler.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduleManagement> managementList = new ArrayList<>();

    private String title;
    private String content;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Comment> comments;

    private String weather;

    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "update_at")
    @UpdateTimestamp // UPDATE 시 자동으로 값을 채워줌
    private LocalDateTime updateAt = LocalDateTime.now();

    public void removeUser(User user) {
        this.managementList.removeIf(scheduleManagement -> scheduleManagement.getUser() == user);
    }

    public List<User> findUsers() {
        return this.getManagementList().stream().map(ScheduleManagement::getUser).toList();
    }

    public List<Long> findUserIds() {
        return this.getManagementList().stream().map(s -> s.getUser().getId()).toList();
    }
}
