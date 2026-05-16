package dev.ohhoonim.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import dev.ohhoonim.post.application.PostDto;
import dev.ohhoonim.post.application.PostService;
import dev.ohhoonim.post.endpoint.PostHandler;
import dev.ohhoonim.post.endpoint.PostRouter;

@WebMvcTest({PostRouter.class, PostHandler.class})
public class BindToRouterFunctionTest {

    // mock test이지만 mockMvc를 사용하지 않는다. 
    
    RestTestClient client;

    @Autowired
    RouterFunction<ServerResponse> postRoute;

    @MockitoBean
    PostService postService;

    @BeforeEach
    void setup() {
        this.client = RestTestClient.bindToRouterFunction(this.postRoute).build();
    }


    @Test
    void postListTest() {

        when(postService.postList()).thenReturn(List.of(new PostDto(1L, "title", "contents")));

        var exchanged = client.get().uri("/posts").accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<PostDto>>() {});

        assertThat(exchanged.returnResult().getResponseBody()).hasSize(1);
    }

}
