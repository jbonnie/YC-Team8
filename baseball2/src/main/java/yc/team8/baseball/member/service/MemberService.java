package yc.team8.baseball.member.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yc.team8.baseball.member.domain.Member;
import yc.team8.baseball.member.dto.LoginDto;
import yc.team8.baseball.member.dto.MemberDto;
import yc.team8.baseball.member.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // 전달받은 parameter로 유저 찾기, 존재하지 않을 시 null 반환
    public Member getMemberWithDto(LoginDto loginDto) {
        Member member = memberRepository.findByLoginIdAndPassword(loginDto.getLoginId(), loginDto.getPassword()).orElse(null);
        return member;
    }
    public Member getMemberWithLoginId(String loginId) {
        Member member = memberRepository.findByLoginId(loginId).orElse(null);
        return member;
    }
    public Member getMemberWithId(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        return member;
    }
    public Member getMemberWithNickname(String nickname) {
        Member member = memberRepository.findByNickname(nickname).orElse(null);
        return member;
    }

    // memberDto로 받은 데이터 엔티티로 변환 후 DB에 저장
    public Member createMember(MemberDto memberDto) {
        Member memberEntity = memberDto.toEntity();
        Member saved = memberRepository.save(memberEntity);
        return saved;
    }

    // id에 해당하는 유저 DB에서 삭제
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        if(member != null) {
            memberRepository.delete(member);
        }
    }

    // memberDto로 받은 유저 정보로 DB에 있던 기존의 유저 정보 교체
    public void updateMember(MemberDto memberDto) {
        Member member = memberRepository.findById(memberDto.getId()).orElse(null);
        if(member != null) {
            member.setLoginId(memberDto.getLoginId());
            member.setPassword(memberDto.getPassword());
            member.setNickname(memberDto.getNickname());
            member.setTeam(memberDto.getTeam());
            memberRepository.save(member);
        }
    }
}
