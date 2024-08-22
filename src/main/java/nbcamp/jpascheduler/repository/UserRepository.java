package nbcamp.jpascheduler.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nbcamp.jpascheduler.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public User save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }

        return user;
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }
}
