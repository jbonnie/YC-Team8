package yc.team8.baseball.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yc.team8.baseball.post.domain.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndDeletionFalse(long id);

    List<Post> findByDeletionFalse();

    List<Post> findByDeletionFalseAndPostType(int postType);
}
