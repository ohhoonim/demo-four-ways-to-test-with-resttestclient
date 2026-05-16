package dev.ohhoonim.post;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.client.RestTestClient;
import dev.ohhoonim.post.application.PostDto;
import dev.ohhoonim.post.application.PostService;
import dev.ohhoonim.post.endpoint.PostController;

@WebMvcTest(PostController.class)
public class BBindToMockMvcTest {
    
    // 특정 controller에 대한 mock test 방식이다.
    // @MockitoBean을 사용하면 기본적인 mock 설정을 자동으로 해준다.

    @Autowired
    MockMvcTester mockMvcTester;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    PostService postService; 

    RestTestClient restTestClient;

    @Test
    void postListNotClientTest() {
        // @MockitoBean 을 사용하면 자동 mock처리된다. 아래 코드는 굳이 넣어본거임.
        when(postService.postList()).thenReturn(List.of(new PostDto(1L, "title", "contents")));
        // 여기서는 fluent api를 지원하는 MockMvcTester를 사용해봤다.
        mockMvcTester.get().uri("/mvc/posts")
            .assertThat().hasStatusOk()
            .apply(print())
            .bodyJson().extractingPath("$").asArray().hasSize(1);
    }


    @BeforeEach
    void setup() {
        // 참고로 2026년 5월 현재 RestDoc은 공식적으로 RestTestClient를 
        // 지원하지 않으므로 MockMvc를 Wrapping하는 방법을 사용해야한다. 
        // 하단 주석 참고
        this.restTestClient = RestTestClient.bindTo(mockMvc).build();
    }

    @Test
    void postListClientTest() {
        restTestClient.get().uri("/mvc/posts/{id}", 1L)
            .exchangeSuccessfully();
            
    }

}


/* 

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class ApiDocumentationTest {

    private RestTestClient restTestClient;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();

        this.restTestClient = RestTestClient.bindTo(mockMvc).build();
    }

    @Test
    void user_get() {
        this.restTestClient.get()
                .uri("/users/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("user-get",
                        pathParameters(
                                parameterWithName("id").description("사용자 식별자")
                        )
                ));
    }
}

*/