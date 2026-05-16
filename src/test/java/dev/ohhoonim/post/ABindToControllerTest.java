package dev.ohhoonim.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;
import dev.ohhoonim.post.application.PostDto;
import dev.ohhoonim.post.application.PostService;
import dev.ohhoonim.post.endpoint.PostController;

public class ABindToControllerTest {

    // 특정 controller에 대한 mock 테스트 방식이다. 
    // @WebMvcTest('컨트롤러') 를 사용하는게 낫다. 


    private RestTestClient restClientTest;
    private PostService postService;

    @BeforeEach
    void setup() {
        this.postService = mock(PostService.class);
        this.restClientTest =
                RestTestClient.bindToController(new PostController(this.postService)).build();
    }

    @Test
    void findPostTest() {

        given(postService.postDetail(eq(1L))).willReturn(new PostDto(1L, "title 1", "contents 1"));

        var exchanged =
                restClientTest.get().uri("/mvc/posts/{id}", 1L).accept(MediaType.APPLICATION_JSON)
                        .exchange().expectBody(PostDto.class).returnResult();

        assertAll(() -> assertThat(exchanged.getStatus()).isEqualTo(HttpStatus.OK),
                () -> assertThat(exchanged.getResponseBody()).isInstanceOf(PostDto.class),
                () -> assertThat(exchanged.getResponseBody().title()).isEqualTo("title 1"));

    }
}
