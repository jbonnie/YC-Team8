package yc.team8.baseball.scrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yc.team8.baseball.scrap.domain.Scrap;
import yc.team8.baseball.scrap.domain.ScrapCompositeKey;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, ScrapCompositeKey> {
}
