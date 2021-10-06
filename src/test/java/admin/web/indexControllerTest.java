package admin.web;

import admin.domain.user.QUser;
import admin.domain.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class indexControllerTest {

	@PersistenceContext
	EntityManager em;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@WithMockUser(roles = "USER")
	public void load_main_page_test() {
		String body = this.testRestTemplate.getForObject("/", String.class);
		assertThat(body).contains("Welcome to my web service.");
	}

	@Test
	public void contextLoads() {
		User user = new User();
		em.persist(user);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QUser qUser = QUser.user;

		User result = query.selectFrom(qUser).fetchOne();

		Assertions.assertThat(result).isEqualTo(user);
		Assertions.assertThat(result.getId()).isEqualTo(user.getId());
	}
}