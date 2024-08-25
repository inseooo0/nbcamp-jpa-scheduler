package nbcamp.jpascheduler.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.LoginRequestDto;
import nbcamp.jpascheduler.dto.UserCreateDto;
import nbcamp.jpascheduler.dto.UserResponseDto;
import nbcamp.jpascheduler.dto.UserUpdateDto;
import nbcamp.jpascheduler.jwt.JwtUtil;
import nbcamp.jpascheduler.service.UserService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserCreateDto requestDto) {
        User saved = userService.save(requestDto);
        String token = jwtUtil.createToken(saved.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new ResponseEntity<>(modelMapper.map(saved, UserResponseDto.class),
                headers, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        User user = userService.findByEmail(requestDto.getEmail());
        String token = jwtUtil.createToken(user.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new ResponseEntity<>(modelMapper.map(user, UserResponseDto.class),
                headers, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return modelMapper.map(user, UserResponseDto.class);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userService.findAll();
        List<UserResponseDto> dtoList = new ArrayList<>();

        for (User user : userList) {
            dtoList.add(modelMapper.map(user, UserResponseDto.class));
        }
        return dtoList;
    }

    @PutMapping("/{userId}")
    public UserResponseDto updateUser(@PathVariable Long userId,
                                      @RequestBody UserUpdateDto requestDto) {
        User user = userService.updateUser(userId, requestDto);
        return modelMapper.map(user, UserResponseDto.class);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.removeById(userId);
        return "ok";
    }
}
