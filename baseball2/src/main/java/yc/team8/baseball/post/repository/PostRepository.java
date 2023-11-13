package yc.team8.baseball.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yc.team8.baseball.post.domain.Post;

import java.util.ArrayList;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 구단, 게시판 종류로 구분하여 전체 게시글 arraylist로 가져오기
    ArrayList<Post> findAllByPostTypeAndTeamName(int postType, String teamName);
}
