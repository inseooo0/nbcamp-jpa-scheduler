package nbcamp.jpascheduler.service;

import jakarta.transaction.Transactional;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.LoginRequestDto;
import nbcamp.jpascheduler.dto.UserCreateDto;
import nbcamp.jpascheduler.dto.UserUpdateDto;
import nbcamp.jpascheduler.exception.ApiException;
import nbcamp.jpascheduler.exception.CommonErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static nbcamp.jpascheduler.domain.UserRole.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void saveAdmin() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName("userA");
        userCreateDto.setEmail("userA@email.com");
        userCreateDto.setPassword("0000");
        userCreateDto.setAdminToken(ADMIN_TOKEN);

        User saved = userService.save(userCreateDto);
        User findUser = userService.findById(saved.getId());
        Assertions.assertThat(findUser).isEqualTo(saved);
        Assertions.assertThat(findUser.getUserRole()).isEqualTo(ADMIN);

    }

    @Test
    @Transactional
    public void saveNotAdmin() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName("userA");
        userCreateDto.setEmail("userA@email.com");
        userCreateDto.setPassword("0000");
        userCreateDto.setAdminToken("1234"); // 잘못된 admin token -> 일반 유저로 저장

        User saved = userService.save(userCreateDto);
        User findUser = userService.findById(saved.getId());

        Assertions.assertThat(findUser).isEqualTo(saved);
        Assertions.assertThat(findUser.getUserRole()).isEqualTo(USER);
    }

    @Test
    @Transactional
    public void 회원_이름_중복_예외() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName("userA");
        userCreateDto.setEmail("userA@email.com");
        userCreateDto.setPassword("0000");

        User saved = userService.save(userCreateDto);

        UserCreateDto userCreateDto2 = new UserCreateDto();
        userCreateDto2.setName("userA");
        userCreateDto2.setEmail("userB@email.com");
        userCreateDto2.setPassword("0000");

        Assertions.assertThatThrownBy(() -> userService.save(userCreateDto2))
                .isInstanceOf(ApiException.class);
    }

    @Test
    @Transactional
    public void 회원_이메일_중복_예외() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName("userA");
        userCreateDto.setEmail("userA@email.com");
        userCreateDto.setPassword("0000");

        User saved = userService.save(userCreateDto);

        UserCreateDto userCreateDto2 = new UserCreateDto();
        userCreateDto2.setName("userB");
        userCreateDto2.setEmail("userA@email.com");
        userCreateDto2.setPassword("0000");

        Assertions.assertThatThrownBy(() -> userService.save(userCreateDto2))
                .isInstanceOf(ApiException.class);
    }

    @Test
    @Transactional
    public void login() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName("userA");
        userCreateDto.setEmail("userA@email.com");
        userCreateDto.setPassword("0000");
        userCreateDto.setAdminToken(ADMIN_TOKEN);

        User saved = userService.save(userCreateDto);

        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(saved.getEmail());
        loginRequestDto.setPassword(userCreateDto.getPassword());

        User login = userService.login(loginRequestDto);
        Assertions.assertThat(saved).isEqualTo(login);
    }

    @Test
    @Transactional
    public void login_fail() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName("userA");
        userCreateDto.setEmail("userA@email.com");
        userCreateDto.setPassword("0000");
        userCreateDto.setAdminToken(ADMIN_TOKEN);

        User saved = userService.save(userCreateDto);

        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(saved.getEmail());
        loginRequestDto.setPassword("0001");

        Assertions.assertThatThrownBy(() -> userService.login(loginRequestDto))
                .isInstanceOf(ApiException.class)
                .hasFieldOrPropertyWithValue("errorCode", CommonErrorCode.INCORRECT_LOGIN_INFO);
    }

    @Test
    @Transactional
    public void update() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName("userA");
        userCreateDto.setEmail("userA@email.com");
        userCreateDto.setPassword("0000");
        userCreateDto.setAdminToken(ADMIN_TOKEN);

        User saved = userService.save(userCreateDto);

        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setName("UserB");
        userUpdateDto.setEmail("UserB@email.com");

        User updated = userService.updateUser(saved.getId(), userUpdateDto);
        User findUser = userService.findById(updated.getId());

        Assertions.assertThat(findUser).isEqualTo(updated);
        Assertions.assertThat(saved.getId()).isEqualTo(findUser.getId());
        Assertions.assertThat(findUser.getName()).isEqualTo("UserB");
        Assertions.assertThat(findUser.getEmail()).isEqualTo("UserB@email.com");
    }

}