package h06.session.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String board;

    private String title;

    @Column(length = 1000)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String board, String title, String content) { //constructor for the test... why can't it be used in actual execution?
        this.title = title;
    }
}
