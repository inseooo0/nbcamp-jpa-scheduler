package nbcamp.jpascheduler.repository;

import nbcamp.jpascheduler.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}
