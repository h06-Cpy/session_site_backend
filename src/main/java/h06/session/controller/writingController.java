package h06.session.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import h06.session.dto.BoardDto;
import h06.session.dto.PostDto;
import h06.session.entities.Comment;
import h06.session.entities.Post;
import h06.session.service.WritingService;

import h06.session.vo.NewCommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class writingController {
    private final WritingService writingService;


    @GetMapping("/")
    public ResponseEntity<List<BoardDto>> mainBoard() {
        List<BoardDto> response = writingService.findPosts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto.Response> postDetail(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(writingService.findOnePost(id));
    }


    @PostMapping("/post")
    public String newPost(@RequestBody Post post) {
        writingService.writePost(post);
        return "success";
    }

    @PostMapping("/comment")
    public String newComment(@RequestBody NewCommentVo commentVo) {
        writingService.writeComment(commentVo);
        return "success";
    }

}
