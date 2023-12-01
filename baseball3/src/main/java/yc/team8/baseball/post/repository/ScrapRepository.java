package yc.team8.baseball.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yc.team8.baseball.post.domain.Scrap;

import java.util.ArrayList;
import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    ArrayList<Scrap> findAllByMemberId(Long memberId);
    Optional<Scrap> findByPostId(Long postId);
}
