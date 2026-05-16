package dev.ohhoonim.post.endpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class PostRouter {
    
    @Bean
    RouterFunction<ServerResponse> postRoute(PostHandler handler) {
        return RouterFunctions.route().path("/posts", builder -> builder
           .GET("", handler.list) 
           .GET("/{id}", handler.post)
        ).build();
    }
}
