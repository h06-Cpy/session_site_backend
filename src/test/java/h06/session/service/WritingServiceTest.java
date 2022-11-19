package h06.session.service;

import h06.session.entities.Comment;
import h06.session.entities.Post;
import h06.session.repository.WritingRepository;
import h06.session.vo.NewCommentVo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class WritingServiceTest {

    @Autowired WritingService writingService;
    @Autowired WritingRepository writingRepository;
    @Test
    public void 글쓰기() throws Exception {
        //given
        Post post = new Post();

        //when
        Long postId = writingService.writePost(post);
        //then
        Assertions.assertThat(post).isEqualTo(writingRepository.findOnePost(postId));
    }

    @Test
    public void 댓글쓰기() throws Exception {
        //given
        NewCommentVo commentVo = new NewCommentVo("test content", "test date", 1L);
        //when
        Long commentId = writingService.writeComment(commentVo);

        Comment newComment = writingRepository.findOneComment(commentId);
        //then
        Assertions.assertThat(newComment.getContent()).isEqualTo(commentVo.getContent());
        Assertions.assertThat(newComment.getDate()).isEqualTo(commentVo.getDate());

    }
}