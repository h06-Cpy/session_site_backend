package h06.session.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import h06.session.entities.Post;
import h06.session.service.WritingService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class writingController {
    private final WritingService writingService;


    @GetMapping("/")
    public ResponseEntity<List<ObjectNode>> mainBoard() {
        ObjectMapper mapper = new ObjectMapper();
        List<Post> posts = writingService.findPosts();
        List<ObjectNode> response = posts.stream()
                .map(post -> {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("title", post.getTitle());
                    objectNode.put("date", post.getDate());
                    objectNode.put("commentNum", post.getComments().size());
                    return objectNode;
                })
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/post")
    public String newPost(@RequestBody Post post) {
        writingService.writePost(post);
        return "success";
    }

}
