package h06.session.dto;

import h06.session.entities.Post;

public class BoardDto {

    private Long postId;
    private String title;
    private String date;
    private int commentNum;

    public BoardDto(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.date = post.getDate();
        this.commentNum = post.getComments().size();
    }
}
