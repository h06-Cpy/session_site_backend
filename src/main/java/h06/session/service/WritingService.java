package h06.session.service;

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

    public Post findOnePost(Long postId) {
        return writingRepository.findOnePost(postId);
    }

    public List<Post> findPosts() {
        return writingRepository.findAllPost();
    }
}
