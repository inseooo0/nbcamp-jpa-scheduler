package nbcamp.jpascheduler.controller;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.LoginRequestDto;
import nbcamp.jpascheduler.dto.UserCreateDto;
import nbcamp.jpascheduler.dto.UserResponseDto;
import nbcamp.jpascheduler.dto.UserUpdateDto;
import nbcamp.jpascheduler.annotation.AuthorizationMethod;
import nbcamp.jpascheduler.jwt.JwtUtil;
import nbcamp.jpascheduler.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @AuthorizationMethod
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserCreateDto requestDto) {
        User saved = userService.save(requestDto);
        String token = jwtUtil.createToken(saved);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new ResponseEntity<>(UserResponseDto.of(saved),
                headers, HttpStatus.OK);
    }

    @AuthorizationMethod
    @GetMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto requestDto) {

        User user = userService.login(requestDto);
        String token = jwtUtil.createToken(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new ResponseEntity<>(UserResponseDto.of(user),
                headers, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return UserResponseDto.of(user);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userService.findAll();
        List<UserResponseDto> dtoList = new ArrayList<>();

        for (User user : userList) {
            dtoList.add(UserResponseDto.of(user));
        }
        return dtoList;
    }

    @PutMapping("/{userId}")
    public UserResponseDto updateUser(@PathVariable Long userId,
                                      @RequestBody UserUpdateDto requestDto) {
        User user = userService.updateUser(userId, requestDto);
        return UserResponseDto.of(user);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.removeById(userId);
        return "ok";
    }
}
