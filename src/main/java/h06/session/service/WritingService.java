package h06.session.service;

import h06.session.dto.BoardDto;
import h06.session.dto.PostDto;
import h06.session.entities.Comment;
import h06.session.entities.Post;
import h06.session.repository.WritingRepository;
import h06.session.vo.NewCommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<BoardDto> findPosts() {
        List<Post> posts = writingRepository.findAllPost();
        return posts.stream().map(BoardDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void deletePost(Long postId) {
        writingRepository.deletePost(postId);
    }

    @Transactional
    public Long writeComment(NewCommentVo commentVo) {
        Post post = writingRepository.findOnePost(commentVo.getPostId());
        Comment comment = Comment.builder()
                .content(commentVo.getContent())
                .post(post).build();
        writingRepository.saveComment(comment);
        return comment.getId();
    }
}
