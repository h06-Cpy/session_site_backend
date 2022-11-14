package h06.session.service;

import h06.session.controller.PostDto;
import h06.session.entities.Comment;
import h06.session.entities.Post;
import h06.session.repository.WritingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WritingService {

    private final WritingRepository writingRepository;

    @Transactional
    public Long writePost(Post post) {
        writingRepository.savePost(post);
        return post.getId(); // added for the test
    }

    public PostDto.Response findOnePost(Long postId) {
        Post post = writingRepository.findOnePost(postId);
        return new PostDto.Response(post);
    }

    public List<Post> findPosts() {
        return writingRepository.findAllPost();
    }

    @Transactional
    public Long writeComment(Comment comment) {
        writingRepository.saveComment(comment);
        return comment.getId();
    }
}
