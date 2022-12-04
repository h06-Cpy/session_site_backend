package h06.session.service;

import h06.session.dto.BoardDto;
import h06.session.dto.PostDto;
import h06.session.entities.Comment;
import h06.session.entities.Post;
import h06.session.repository.CommentRepository;
import h06.session.repository.PostRepository;
import h06.session.vo.NewCommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WritingService {


    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long writePost(Post post) {
        postRepository.save(post);
        return post.getId(); // added for the test
    }

    public PostDto.Response findOnePost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        return new PostDto.Response(post.orElseGet(Post::new));
    }

    public List<BoardDto> findPosts(String board, Pageable pageable) {
        List<Post> posts = postRepository.findByBoard(board, pageable);
        return posts.stream().map(BoardDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public Long writeComment(NewCommentVo commentVo) throws Exception {
        Optional<Post> post = postRepository.findById(commentVo.getPostId());
        Comment comment = Comment.builder()
                .content(commentVo.getContent())
                .post(post.orElseThrow(()-> new Exception("cannot find post"))).build();
        commentRepository.save(comment);
        return comment.getId();
    }
}
