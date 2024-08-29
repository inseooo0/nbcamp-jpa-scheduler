package nbcamp.jpascheduler.service;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.config.PasswordEncoder;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.domain.UserRole;
import nbcamp.jpascheduler.dto.LoginRequestDto;
import nbcamp.jpascheduler.dto.UserCreateDto;
import nbcamp.jpascheduler.dto.UserUpdateDto;
import nbcamp.jpascheduler.exception.ApiException;
import nbcamp.jpascheduler.exception.CommonErrorCode;
import nbcamp.jpascheduler.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static nbcamp.jpascheduler.domain.UserRole.ADMIN_TOKEN;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User save(UserCreateDto dto) {
        repository.findByEmail(dto.getEmail())
                .ifPresent(u -> {throw new ApiException(CommonErrorCode.INVALID_PARAMETER);});

        repository.findByName(dto.getName())
                .ifPresent(u -> {throw new ApiException(CommonErrorCode.INVALID_PARAMETER);});

        User user = new User(dto.getName(), dto.getEmail(), passwordEncoder.encode(dto.getPassword()));
        if (dto.getAdminToken() != null && dto.getAdminToken().equals(ADMIN_TOKEN)) {
            user.setUserRole(UserRole.ADMIN);
        }
        return repository.save(user);
    }

    public User login(LoginRequestDto dto) {
        User user = findByEmail(dto.getEmail());
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new ApiException(CommonErrorCode.INCORRECT_LOGIN_INFO);
        }
        return user;
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException(CommonErrorCode.INVALID_PARAMETER));
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new ApiException(CommonErrorCode.INVALID_PARAMETER));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    public User updateUser(Long id, UserUpdateDto updateDto) {
        User user = findById(id);
        user.update(updateDto.getName(), updateDto.getEmail());
        return user;
    }

    @Transactional
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
