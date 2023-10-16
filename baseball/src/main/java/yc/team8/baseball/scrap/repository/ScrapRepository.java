package yc.team8.baseball.scrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yc.team8.baseball.scrap.domain.Scrap;
import yc.team8.baseball.scrap.domain.ScrapCompositeKey;

public interface ScrapRepository extends JpaRepository<Scrap, ScrapCompositeKey> {
}
