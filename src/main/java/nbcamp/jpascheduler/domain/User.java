package nbcamp.jpascheduler.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "update_at")
    @UpdateTimestamp // UPDATE 시 자동으로 값을 채워줌
    private LocalDateTime updateAt = LocalDateTime.now();

}
