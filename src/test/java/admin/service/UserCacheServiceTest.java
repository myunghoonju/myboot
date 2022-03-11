package admin.service;


import admin.config.auth.dto.SessionUser;
import admin.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCacheServiceTest {

    @Autowired
    private UserCacheService service;

    @Test
    public void gettingUsername() {
        User a = User.builder().name("a").email("a@mail.com").picture("a.jpg").build();
        SessionUser sessionUser = new SessionUser(a);

        service.getUserName(sessionUser);
        service.getUserName(sessionUser);
        log.info("-----------------------");

        service.getUserName(sessionUser);
        service.getUserName(sessionUser);
        log.info("-----------------------");

        service.getUserName(sessionUser);
        service.getUserName(sessionUser);
        log.info("-----------------------");

        service.getUserName(sessionUser);
        service.getUserName(sessionUser);
        log.info("-----------------------");

        service.getUserName(sessionUser);
        service.getUserName(sessionUser);
        log.info("-----------------------");

        service.getUserName(sessionUser);
        service.getUserName(sessionUser);
        log.info("-----------------------");
    }


}