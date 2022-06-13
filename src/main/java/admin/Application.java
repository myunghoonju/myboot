package admin;

import admin.config.property.ConfigEnum;
import admin.message.Receiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.core.StringRedisTemplate;

@Slf4j
@EnableRabbit
@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        log.error("---redis host :: {}", ConfigEnum.REDIS_INFO.getHost());
        log.error("---redis host :: {}", ConfigEnum.REDIS_INFO.getPort());
/*
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        Receiver receiver = ctx.getBean(Receiver.class);

        while (receiver.getCnt() == 0) {
            log.info("-----sending message-----");
            template.convertAndSend("chat", "hello from redis");
            Thread.sleep(500L);
        }

        System.exit(0);*/
    }
}
