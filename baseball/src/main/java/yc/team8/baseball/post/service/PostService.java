package yc.team8.baseball.post.service;

import yc.team8.baseball.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post addPost(Post post);

    Optional<Post> getPostById(long id);

    List<Post> getAllPostList();

    List<Post> getPostListByType(int postType);

    void deletePost(long id);

}
