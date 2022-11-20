package h06.session.dto;

import h06.session.entities.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDto {

    private Long postId;
    private String title;
    private LocalDateTime createdDate;
    private int commentNum;

    public BoardDto(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.createdDate = post.getCreatedDate();
        this.commentNum = post.getComments().size();
    }
}
