package nbcamp.jpascheduler.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

    @PersistenceContext
    EntityManager em;

    public Comment save(Comment comment) {
        em.persist(comment);
        return comment;
    }

    public Comment findById(Long id) {
        return em.find(Comment.class, id);
    }
}
