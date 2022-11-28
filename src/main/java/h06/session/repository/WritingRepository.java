package h06.session.repository;

import h06.session.entities.Comment;
import h06.session.entities.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WritingRepository {
    private final EntityManager em;

    public void savePost(Post post) {
        em.persist(post);
    }

    public Post findOnePost(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAllPost() {
        return em.createQuery("select p from Post p", Post.class).getResultList();
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = em.find(Post.class, id);
        em.remove(post);
    }

        public void saveComment(Comment comment) {
        em.persist(comment);
    }

    public Comment findOneComment(Long id) {
        return em.find(Comment.class, id);
    }

}
