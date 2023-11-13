package yc.team8.baseball.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yc.team8.baseball.post.domain.Post;
import yc.team8.baseball.post.dto.PostDto;
import yc.team8.baseball.post.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> getPostById(long id) {
        return postRepository.findByIdAndDeletionFalse(id);
    }
    @Override
    public List<Post> getAllPostList() {
        return postRepository.findByDeletionFalse();
    }
    @Override
    public List<Post> getPostListByType(int postType) {
        return postRepository.findByDeletionFalseAndPostType(postType);
    }

  /*  dto 버전
    public Iterable<PostDto> findAll() {
        return postRepository.findAll().stream().map(post -> convertToDto(post)).collect(Collectors.toList());
    }

    public Optional<PostDto> findById(Long id) {
        return Optional.of(convertToDto(postRepository.findById(id).orElse(null)));
    }*/

    @Override
    public void deletePost(long id) {
        postRepository.findById(id)
                .ifPresentOrElse(post -> {
                    post.setDeletion(true);
                    postRepository.save(post);
                    }, () -> {throw new IllegalArgumentException("not exist post : %s".formatted(id));}
                            );
    }

    private PostDto convertToDto(Post post) {
        return new PostDto(post.getId(), post.getPostType(), post.getTitle(), post.getContents(), post.getWriterId(),post.isDeletion());
    }
}
