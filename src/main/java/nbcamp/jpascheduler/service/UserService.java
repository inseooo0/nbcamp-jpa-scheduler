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
import nbcamp.jpascheduler.exception.ErrorCode;
import nbcamp.jpascheduler.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static nbcamp.jpascheduler.domain.UserRole.ADMIN_TOKEN;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User save(UserCreateDto dto) {
        Optional<User> checkEmail = repository.findByEmail(dto.getEmail());
        if (checkEmail.isPresent()) throw new ApiException(CommonErrorCode.INVALID_PARAMETER);

        Optional<User> checkName = repository.findByName(dto.getName());
        if (checkName.isPresent()) throw new ApiException(CommonErrorCode.INVALID_PARAMETER);

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
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
        Optional<User> checkId = repository.findById(id);
        if (checkId.isEmpty()) throw new ApiException(CommonErrorCode.INVALID_PARAMETER);
        return checkId.get();
    }

    public User findByEmail(String email) {
        Optional<User> checkEmail = repository.findByEmail(email);
        if (checkEmail.isEmpty()) throw new ApiException(CommonErrorCode.INVALID_PARAMETER);

        return checkEmail.get();
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    public User updateUser(Long id, UserUpdateDto updateDto) {
        Optional<User> checkId = repository.findById(id);
        if (checkId.isEmpty()) throw new ApiException(CommonErrorCode.INVALID_PARAMETER);

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
