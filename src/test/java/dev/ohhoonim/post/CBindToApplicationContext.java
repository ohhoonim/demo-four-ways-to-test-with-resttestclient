package dev.ohhoonim.post;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;
import dev.ohhoonim.post.application.PostDto;

@SpringBootTest
public class CBindToApplicationContext {

    // 실제 http 연결을 사용하지 않지만 애플리케이션 전체를 구동하는 테스트이다. 

    @Autowired
    WebApplicationContext context;

    RestTestClient client;

    @BeforeEach
    void setup() {
        this.client = RestTestClient.bindToApplicationContext(this.context).build();
    }

    @Test
    void postListTest() {
        var exchanged = client.get().uri("/mvc/posts").accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<PostDto>>() {});

        // PostPersistantPostgresAdaptor 참고
        assertThat(exchanged.returnResult().getResponseBody()).hasSize(8);
    }

}
