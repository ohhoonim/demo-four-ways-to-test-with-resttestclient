package dev.ohhoonim.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DBindToServerTest {

    // 실제 http 요청을 포함하여 모든 기능을 테스트한다. 
    // MSA 환경이나, 외부 시스템 연동 환경에서 테스트에 유용하다.

    // server port가 테스트시 랜덤으로 동작하므로 @LocalServerPort 의 도움을 받는다.
    @LocalServerPort
    int serverPort;

    RestTestClient client;

    @BeforeEach
    void setup() {
        this.client = RestTestClient.bindToServer().baseUrl("http://localhost:" + this.serverPort)
                .build();
    }

    @Test
    void postListTest() {
        var response = client.get().uri("/posts").accept(MediaType.APPLICATION_JSON)
                .exchangeSuccessfully().expectBody();

        assertAll(
            () -> response.jsonPath("$").isArray(),
            () -> response.jsonPath("$[0].title").isEqualTo("title 1")
        );

    }
}
