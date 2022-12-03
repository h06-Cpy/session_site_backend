package h06.session.service;

import h06.session.entities.Comment;
import h06.session.entities.Post;
import h06.session.repository.CommentRepository;
import h06.session.repository.PostRepository;
import h06.session.repository.WritingRepository;
import h06.session.vo.NewCommentVo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class WritingServiceTest {

    @Autowired WritingService writingService;
//    @Autowired WritingRepository writingRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void 글쓰기() throws Exception {
        //given
        Post post = new Post();

        //when
        Long postId = writingService.writePost(post);
        //then
        Assertions.assertThat(post).isEqualTo(postRepository.findById(postId).get());
    }

    @Test
    public void 글삭제() throws Exception {
        //given
        Post newPost = new Post();
        Long postId = writingService.writePost(newPost);

        //when
        writingService.deletePost(postId);
        Optional<Post> foundPost = postRepository.findById(postId);
        //then
        Assertions.assertThat(foundPost.isEmpty()).isEqualTo(true);
    }

    @Test
    public void 댓글쓰기() throws Exception {
        //given
        Post post = new Post();
        Long postId = writingService.writePost(post);
        NewCommentVo commentVo = new NewCommentVo("test content", postId);
        //when
        Long commentId = writingService.writeComment(commentVo);

        Comment newComment = commentRepository.findById(commentId).get();
        //then
        Assertions.assertThat(newComment.getContent()).isEqualTo(commentVo.getContent());

    }
}