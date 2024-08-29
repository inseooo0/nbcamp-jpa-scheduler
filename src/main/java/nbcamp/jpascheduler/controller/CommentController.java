package nbcamp.jpascheduler.controller;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Comment;
import nbcamp.jpascheduler.dto.CommentCreateDto;
import nbcamp.jpascheduler.dto.CommentResponseDto;
import nbcamp.jpascheduler.dto.CommentUpdateDto;
import nbcamp.jpascheduler.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentResponseDto saveComment(@RequestBody CommentCreateDto requestDto) {
        Comment comment = commentService.save(requestDto);

        return CommentResponseDto.of(comment);
    }

    @GetMapping("/{commentId}")
    public CommentResponseDto getComment(@PathVariable Long commentId) {
        Comment comment = commentService.findById(commentId);

        return CommentResponseDto.of(comment);
    }

    @GetMapping
    public List<CommentResponseDto> getAllComment() {
        List<Comment> comments = commentService.findAll();
        List<CommentResponseDto> dtoList = new ArrayList<>();
        for (Comment comment : comments) {
            dtoList.add(CommentResponseDto.of(comment));
        }
        return dtoList;
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateDto requestDto) {
        Comment comment = commentService.update(commentId, requestDto);
        return CommentResponseDto.of(comment);
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        commentService.removeById(commentId);
        return "ok";
    }
}
