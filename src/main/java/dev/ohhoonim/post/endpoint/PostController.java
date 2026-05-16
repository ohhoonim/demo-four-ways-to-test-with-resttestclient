package dev.ohhoonim.post.endpoint;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.ohhoonim.post.application.PostDto;
import dev.ohhoonim.post.application.PostService;

@RestController
@RequestMapping("/mvc")
public class PostController {

    private final PostService postService;

    public PostController (PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<PostDto> list() {
        return postService.postList();
    }

    @GetMapping("/posts/{id}")
    public PostDto post(@PathVariable("id") Long id) {
        return postService.postDetail(id);
    }
}
