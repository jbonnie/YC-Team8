package yc.team8.baseball.scrap.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import yc.team8.baseball.member.domain.Member;
import yc.team8.baseball.post.domain.Post;
import yc.team8.baseball.scrap.domain.Scrap;
import yc.team8.baseball.scrap.domain.ScrapId;
import yc.team8.baseball.scrap.dto.ScrapDto;
import yc.team8.baseball.scrap.repository.ScrapRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScrapService {
    @Autowired
    private ScrapRepository scrapRepository;
    @Autowired
    private ScrapRepository memberRepository;
    @Autowired
    private ScrapRepository postRepository;

//    public Scrap getScrap(Long id) {
//        Scrap scrap = scrapRepository.findById(id).orElse(null);
//        return scrap;
//    }

    public ArrayList<Scrap> getScrapsByUser(Long userId) {
        ArrayList<Scrap> scrapArr = scrapRepository.findAllByUserId(userId);
        return scrapArr;
    }

    public Scrap addScrap(ScrapDto scrapDto) {
        Member member = memberRepository.findById(scrapDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + scrapDto.getUserId())).getMember();
        Post post = postRepository.findById(scrapDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + scrapDto.getPostId())).getPost();
        Scrap scrap = scrapDto.toEntity(member, post);

        return scrapRepository.save(scrap);
    }

    public void deleteScrap(Long scrapId) {
        Scrap target = scrapRepository.findById(scrapId).orElse(null);
        if (target != null) {
            scrapRepository.delete(target);
        }
//        Optional<Scrap> target = scrapRepository.findById(scrapId);
//        target.ifPresent(scrapRepository::delete);
    }
}