package h06.session.service;

import h06.session.entities.Comment;
import h06.session.entities.Post;
import h06.session.repository.WritingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class WritingServiceTest {

    @Autowired WritingService writingService;

    @Test
    public void 글쓰기() throws Exception {
        //given
        Post post = new Post();

        //when
        Long postId = writingService.writePost(post);
        //then
        Assertions.assertThat(post).isEqualTo(writingService.findOnePost(postId));
    }
}