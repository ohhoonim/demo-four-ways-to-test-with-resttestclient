package dev.ohhoonim.post.endpoint;

import static org.springframework.web.servlet.function.ServerResponse.ok;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerResponse;
import dev.ohhoonim.post.application.PostService;

@Component
public class PostHandler {

    public final HandlerFunction<ServerResponse> list;
    public final HandlerFunction<ServerResponse> post;

    public PostHandler (PostService postService) {
        this.list = req -> {
            return ok().body(postService.postList());
        };
        this.post = req -> {
            Long id = Long.valueOf(Objects.requireNonNull(req.pathVariable("id")));
            return ok().body(postService.postDetail(id));
        };
        
    }

}
