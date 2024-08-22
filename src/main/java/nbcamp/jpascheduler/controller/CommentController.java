package nbcamp.jpascheduler.controller;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Comment;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.CommentCreateDto;
import nbcamp.jpascheduler.dto.CommentResponseDto;
import nbcamp.jpascheduler.dto.CommentUpdateDto;
import nbcamp.jpascheduler.dto.UserResponseDto;
import nbcamp.jpascheduler.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @PostMapping
    public CommentResponseDto saveComment(@RequestBody CommentCreateDto requestDto) {
        Comment comment = commentService.save(requestDto);
        User user = comment.getUser();
        CommentResponseDto responseDto = modelMapper.map(comment, CommentResponseDto.class);
        responseDto.setUser(modelMapper.map(user, UserResponseDto.class));
        return responseDto;
    }

    @GetMapping("/{commentId}")
    public CommentResponseDto getComment(@PathVariable Long commentId) {
        Comment comment = commentService.findById(commentId);
        User user = comment.getUser();
        CommentResponseDto responseDto = modelMapper.map(comment, CommentResponseDto.class);
        responseDto.setUser(modelMapper.map(user, UserResponseDto.class));
        return responseDto;
    }

    @GetMapping
    public List<CommentResponseDto> getAllComment() {
        List<Comment> comments = commentService.findAll();
        List<CommentResponseDto> dtoList = new ArrayList<>();
        for (Comment comment : comments) {
            User user = comment.getUser();
            CommentResponseDto responseDto = modelMapper.map(comment, CommentResponseDto.class);
            responseDto.setUser(modelMapper.map(user, UserResponseDto.class));
            dtoList.add(responseDto);
        }
        return dtoList;
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateDto requestDto) {
        Comment comment = commentService.update(commentId, requestDto);
        User user = comment.getUser();
        CommentResponseDto responseDto = modelMapper.map(comment, CommentResponseDto.class);
        responseDto.setUser(modelMapper.map(user, UserResponseDto.class));
        return responseDto;
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        commentService.removeById(commentId);
        return "ok";
    }
}
