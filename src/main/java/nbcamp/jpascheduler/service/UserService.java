package nbcamp.jpascheduler.service;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.config.PasswordEncoder;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.UserCreateDto;
import nbcamp.jpascheduler.dto.UserUpdateDto;
import nbcamp.jpascheduler.jwt.JwtUtil;
import nbcamp.jpascheduler.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User save(UserCreateDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return repository.save(user);
    }

    public User findById(Long id) {
        Optional<User> checkId = repository.findById(id);
        if (checkId.isEmpty()) throw new IllegalArgumentException();
        return checkId.get();
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    public User updateUser(Long id, UserUpdateDto updateDto) {
        Optional<User> checkId = repository.findById(id);
        if (checkId.isEmpty()) throw new IllegalArgumentException();

        User user = checkId.get();
        user.setName(updateDto.getName());
        user.setEmail(updateDto.getEmail());
        return user;
    }

    @Transactional
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
