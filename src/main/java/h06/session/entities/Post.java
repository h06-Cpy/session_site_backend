package h06.session.entities;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String board;

    private String title;

    private String date;

    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

//    public Post(String title) { //constructor for the test... why it can't be used in actual execution?
//        this.title = title;
//    }
}
