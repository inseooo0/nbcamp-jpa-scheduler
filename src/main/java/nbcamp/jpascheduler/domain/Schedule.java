package nbcamp.jpascheduler.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String title;
    private String content;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "update_at")
    @UpdateTimestamp // UPDATE 시 자동으로 값을 채워줌
    private LocalDateTime updateAt = LocalDateTime.now();

}
