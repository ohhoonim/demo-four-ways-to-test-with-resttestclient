package dev.ohhoonim.post.infra.adaptor;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import dev.ohhoonim.post.activity.out.PostPersistantPort;
import dev.ohhoonim.post.model.Post;

@Component
public class PostPersistantPostgresAdaptor implements PostPersistantPort {

    // 여기서 실제 db를 조회하는 jdbc나 jpa를 사용해야하나 
    // 그냥 샘플데이터 셋을 제공해주는 것으로 갈음.

    private final List<Post> datas = List.of(
        new Post(1L, "title 1", "contents 1"),
        new Post(2L, "title 2", "contents 2"),
        new Post(3L, "title 3", "contents 3"),
        new Post(4L, "title 4", "contents 4"),
        new Post(5L, "title 5", "contents 5"),
        new Post(6L, "title 6", "contents 6"),
        new Post(7L, "title 7", "contents 7"),
        new Post(8L, "title 8", "contents 8")
    );

    @Override
    public List<Post> findAll() {
        return datas;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return datas.stream().filter(p -> id.equals(p.id())).findFirst();
    }
    
}
