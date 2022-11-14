package h06.session.dto;

import h06.session.entities.Comment;
import h06.session.entities.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostDto {

    @Getter
    @NoArgsConstructor
    public static class Response {
        private String postTitle;
        private String postContent;
        private String postDate;
        private List<CommentDto> comments = new ArrayList<>();

        public Response(Post post) {
            this.postContent = post.getContent();
            this.postDate = post.getDate();
            this.postTitle = post.getTitle();
            this.comments = post.getComments().stream()
                    .map(comment -> new CommentDto(comment))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class CommentDto {
        private String commentContent;
        private String commentDate;

        public CommentDto(Comment comment) {
            this.commentContent = comment.getContent();
            this.commentDate = comment.getDate();
        }
    }
}
