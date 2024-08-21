package nbcamp.jpascheduler.controller;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Comment;
import nbcamp.jpascheduler.dto.CommentCreateDto;
import nbcamp.jpascheduler.dto.CommentResponseDto;
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
        Comment saved = commentService.save(requestDto);
        return modelMapper.map(saved, CommentResponseDto.class);
    }

    @GetMapping("/{commentId}")
    public CommentResponseDto getComment(@PathVariable Long commentId) {
        Comment findComment = commentService.findById(commentId);
        return modelMapper.map(findComment, CommentResponseDto.class);
    }

    @GetMapping
    public List<CommentResponseDto> getAllComment() {
        List<Comment> comments = commentService.findAll();
        List<CommentResponseDto> dtoList = new ArrayList<>();
        for (Comment comment : comments) {
            dtoList.add(modelMapper.map(comment, CommentResponseDto.class));
        }
        return dtoList;
    }

}
