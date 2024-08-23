package nbcamp.jpascheduler.service;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.UserCreateDto;
import nbcamp.jpascheduler.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;

    @Transactional
    public User save(UserCreateDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return repository.save(user);
    }

    public User findById(Long id) {
        return repository.findById(id);
    }

    public List<User> findAll() {
        return repository.findAll();
    }
}
