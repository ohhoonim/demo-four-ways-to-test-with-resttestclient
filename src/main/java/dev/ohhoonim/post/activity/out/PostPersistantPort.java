package dev.ohhoonim.post.activity.out;

import java.util.List;
import java.util.Optional;
import dev.ohhoonim.post.model.Post;

public interface PostPersistantPort {

    List<Post> findAll();

    Optional<Post> findById(Long id);

}
