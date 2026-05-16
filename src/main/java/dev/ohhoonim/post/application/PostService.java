package dev.ohhoonim.post.application;

import java.util.List;
import java.util.function.Function;
import org.springframework.stereotype.Component;
import dev.ohhoonim.post.activity.PostQueryActivity;
import dev.ohhoonim.post.model.Post;

@Component
public class PostService {

    private final PostQueryActivity postQueryActivity;

    public PostService(PostQueryActivity postQueryActivity) {
        this.postQueryActivity = postQueryActivity;
    }


    public List<PostDto> postList() {
        List<Post> posts = postQueryActivity.list();
        // audit event 등...후속처리 필요시 
        return posts.stream().map(dtoMapper).toList();
    }


    public PostDto postDetail(Long id) {
        Post post = postQueryActivity.post(id);
        // audit event 등...후속처리 필요시 
        return dtoMapper.apply(post);
    }

    private Function<Post, PostDto> dtoMapper = p -> new PostDto(p.id(), p.title(), p.contents());
}
