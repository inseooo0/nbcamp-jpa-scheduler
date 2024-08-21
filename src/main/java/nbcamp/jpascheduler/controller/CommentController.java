package nbcamp.jpascheduler.controller;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Comment;
import nbcamp.jpascheduler.dto.CommentCreateDto;
import nbcamp.jpascheduler.dto.CommentResponseDto;
import nbcamp.jpascheduler.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
