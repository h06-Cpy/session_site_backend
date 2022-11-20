package h06.session.dto;

import h06.session.entities.Comment;
import h06.session.entities.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostDto {

    @Getter
    @NoArgsConstructor
    public static class Response {
        private String postTitle;
        private String postContent;
        private LocalDateTime createdDate;
        private List<CommentDto> comments = new ArrayList<>();

        public Response(Post post) {
            this.postContent = post.getContent();
            this.createdDate = post.getCreatedDate();
            this.postTitle = post.getTitle();
            this.comments = post.getComments().stream()
                    .map(CommentDto::new)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class CommentDto {
        private String content;
        private LocalDateTime createdDate;

        public CommentDto(Comment comment) {
            this.content = comment.getContent();
            this.createdDate = comment.getCreatedDate();
        }
    }
}
