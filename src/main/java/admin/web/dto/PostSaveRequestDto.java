package admin.web.dto;

import admin.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {

    private String title;
    private String content;
    private String author;
    private Long age;

    @Builder
    public PostSaveRequestDto(String title,
                              String content,
                              String author,
                              Long age) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.age = age;
    }

    public Post toEntity() {
        return Post.builder()
                   .title(title)
                   .content(content)
                   .author(author)
                   .age(age)
                   .build();
    }
}
