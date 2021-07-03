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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import admin.domain.post.Post;
import admin.domain.post.PostRepository;
import admin.web.dto.PostSaveRequestDto;
import admin.web.dto.PostUpdateRequestDto;

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
	public void add_post_test() throws Exception {
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

	@Test
	public void edit_post_test() throws Exception {
		String title = "title_test3";
		String content = "content_test3";
		String author = "JMH";

		Post newpost = postRepository.save(Post.builder()
		.title(title)
		.content(content)
		.author(author)
		.build());

		Long updateId = newpost.getId();
		String expectedTitle = "title_test4";
		String expectedContent = "content_test4";

		PostUpdateRequestDto postUpdateRequestDto = PostUpdateRequestDto.builder()
			.title(expectedTitle)
			.content(expectedContent)
			.build();

		String url = "http://localhost:" + port + "/api/v1/post/" + updateId;
		mockMvc.perform(put(url)
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(postUpdateRequestDto))
		).andExpect(status().isOk());

		Post post = postRepository.findById(newpost.getId()).orElse(Post.builder().build());
		assertThat(post.getTitle()).isEqualTo(expectedTitle);
		assertThat(post.getContent()).isEqualTo(expectedContent);
	}
}