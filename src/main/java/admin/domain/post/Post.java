package admin.domain.post;

import admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor
@DynamicUpdate
@Entity // never use @Setter
public class Post extends BaseTimeEntity {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increament
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private String author;

    @Column
    private Long age;

    @Builder
    public Post(String title,
                String content,
                String author,
                Long age) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.age = age;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
