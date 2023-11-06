package yc.team8.baseball.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yc.team8.baseball.user.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginIdAndPassword(String loginId, String password);
}
