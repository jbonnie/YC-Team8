package yc.team8.baseball.post.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yc.team8.baseball.post.domain.Post;
import yc.team8.baseball.post.dto.PostDto;
import yc.team8.baseball.post.repository.PostRepository;

import java.util.ArrayList;
import java.util.Collections;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // 해당 구단의 해당 게시판 글 전부 불러오기
    public ArrayList<Post> getPosts(String teamName, String postType) {
        int intPostType = 0;
        if(postType.equals("question"))
            intPostType = 1;
        if(postType.equals("talk"))
            intPostType = 2;
        ArrayList<Post> postArr = postRepository.findAllByPostTypeAndTeamName(intPostType, teamName);
        // ArrayList<Post> 업데이트 시간 빠른 순으로 정렬
        Collections.sort(postArr);
        return postArr;
    }

    // 해당 id의 게시글 엔티티 가져오기
    public Post getPost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        return post;
    }

    // postDto로 받은 데이터 엔티티로 변환 후 db에 저장
    public Post createPost(PostDto postDto) {
        Post postEntity = postDto.toEntity();
        Post saved = postRepository.save(postEntity);
        return saved;
    }

    // postDto로 받은 데이터로 db에 있던 게시물 내용 교체
    public Post updatePost(PostDto postDto) {

        Post target = postRepository.findById(postDto.getId()).orElse(null);
        if(target != null) {
            // postDto의 제목과 내용으로 게시물 변경
            target.setTitle(postDto.getTitle());
            target.setContents(postDto.getContents());
            postRepository.save(target);
        }
        return target;
    }

    // id 값에 해당하는 게시물 db에서 삭제
    public void deletePost(Long id) {
        Post target = postRepository.findById(id).orElse(null);
        if(target != null) {
            postRepository.delete(target);
        }
    }
}
