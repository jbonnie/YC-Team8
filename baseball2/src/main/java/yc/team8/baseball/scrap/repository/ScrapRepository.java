package yc.team8.baseball.scrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yc.team8.baseball.post.domain.Post;
import yc.team8.baseball.scrap.domain.Scrap;
import yc.team8.baseball.scrap.domain.ScrapId;

import java.util.ArrayList;
import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    ArrayList<Scrap> findAllByUserId(Long userId);
    ArrayList<Scrap> findByPostId(Long postId);
    ArrayList<Scrap> findByUserIdAndPostId(Long userId, Long postId);
}

