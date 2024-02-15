/*
package admin.domain.post;

import org.junit.After;
import org.junit.Assert;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest // use h2
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @After
    public void cleanUp() {
        postRepository.deleteAll();
    }

    @Test
    public void 글_저장_불러오기() {
        String title = "test_title";
        String content = "test_content";
        postRepository.save(Post.builder()
                .author("JMH")
                .title(title)
                .content(content)
                .build());

        List<Post> postList = postRepository.findAll();

        Post post = postList.get(0);
        Assert.assertEquals(post.getTitle(), title);
        Assert.assertEquals(post.getContent(), content);
    }

    @Test
    public void BaseTimeEntity_save() {
        LocalDateTime now = LocalDateTime.of(2021, 7, 3, 0, 0, 0);
        postRepository.save(Post.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        List<Post> postList = postRepository.findAll();
        Post post = postList.get(0);

        System.out.println(">>> createdDate:: " + post.getCreatedDate() + ", modifiedDate:: " + post.getModifiedDate());
        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }
}*/
