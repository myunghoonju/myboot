package admin.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class indexControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void load_main_page_test() {
		String body = this.testRestTemplate.getForObject("/", String.class);
		assertThat(body).contains("Welcome to my web service.");
	}
}