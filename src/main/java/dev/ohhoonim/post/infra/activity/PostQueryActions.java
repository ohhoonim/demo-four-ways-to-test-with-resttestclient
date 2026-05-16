package dev.ohhoonim.post.infra.activity;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import dev.ohhoonim.post.activity.PostQueryActivity;
import dev.ohhoonim.post.activity.out.PostPersistantPort;
import dev.ohhoonim.post.model.Post;

@Component
public class PostQueryActions implements PostQueryActivity {

    private final PostPersistantPort postPersistantPort;

    public PostQueryActions(PostPersistantPort repository) {
        this.postPersistantPort = repository;
    }

    @Override
    public List<Post> list() {
        return postPersistantPort.findAll();
    }

    @Override
    public Post post(Long id) {
        Optional<Post> post = postPersistantPort.findById(id);
        return  post.orElseThrow(() -> new RuntimeException("post가 존재하지 않습니다."));
    }
    
}
