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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
        PageRequest pageable = PageRequest.of(0, 10);
        List<BoardDto> response = writingService.findPosts("main", pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{board}")
    public ResponseEntity<List<BoardDto>> board(@PathVariable("board") String board,
                                                @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        List<BoardDto> response = writingService.findPosts(board, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/post")
    public ResponseEntity<PostDto.Response> postDetail(@RequestParam("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(writingService.findOnePost(id));
    }


    @PostMapping("/post")
    public String newPost(@RequestBody Post post) {
        writingService.writePost(post);
        return "success";
    }

    @PostMapping("/comment")
    public String newComment(@RequestBody NewCommentVo commentVo) throws Exception {
        writingService.writeComment(commentVo);
        return "success";
    }

}
