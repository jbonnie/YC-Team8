package yc.team8.baseball.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yc.team8.baseball.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginIdAndPassword(String loginId, String password);
    Optional<Member> findByLoginId(String loginId);
    Optional<Member> findByNickname(String nickname);
}
