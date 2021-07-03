package admin.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import admin.domain.post.Post;
import admin.domain.post.PostRepository;
import admin.web.dto.PostSaveRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

	private MockMvc mockMvc;
	@LocalServerPort
	private int port;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void resetTest() {
		postRepository.deleteAll();
	}

	@Test
	public void post_등록_테스트() throws Exception {
		String title = "title_test2";
		String content = "content_test2";
		String author = "JMH";

		String url = "http://localhost:" + port + "/api/v1/post";
		PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
			.title(title)
			.content(content)
			.author(author)
			.build();

		mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(postSaveRequestDto))
		).andExpect(status().isOk());

		List<Post> allPost = postRepository.findAll();
		assertThat(allPost.get(0).getTitle()).isEqualTo(title);
		assertThat(allPost.get(0).getContent()).isEqualTo(content);
		assertThat(allPost.get(0).getAuthor()).isEqualTo(author);
	}
}