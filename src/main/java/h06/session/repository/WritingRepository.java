package h06.session.repository;

import h06.session.entities.Comment;
import h06.session.entities.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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

    public void saveComment(Comment comment) {
        em.persist(comment);
    }

}
