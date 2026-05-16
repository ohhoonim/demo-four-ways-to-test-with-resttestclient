package dev.ohhoonim.post.model;

public record Post(
    Long id,
    String title,
    String contents
) {
    
    public Post{
        if (id == null) {
            throw new RuntimeException("id는 필수입니다.");
        }
    }
}
