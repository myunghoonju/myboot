package admin;

import admin.message.Receiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.core.StringRedisTemplate;

@Slf4j
@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        Receiver receiver = ctx.getBean(Receiver.class);

        while (receiver.getCnt() == 0) {
            log.info("-----sending message-----");
            template.convertAndSend("chat", "hello from redis");
            Thread.sleep(500L);
        }

        System.exit(0);
    }
}
