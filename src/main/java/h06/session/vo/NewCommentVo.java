package h06.session.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewCommentVo {

    private String content;
    private String date;
    private Long postId;

}
