package dev.ohhoonim.post.activity;

import java.util.List;
import dev.ohhoonim.post.model.Post;

public interface PostQueryActivity {

    // 목록 조회
    public List<Post> list() ;
    // 상세 정보 조회
    public Post post(Long id);
}
