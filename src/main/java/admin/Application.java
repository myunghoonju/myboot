package admin;

import admin.config.property.ConfigEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        log.error("---redis host :: {}", ConfigEnum.REDIS_INFO.getHost());
        log.error("---redis host :: {}", ConfigEnum.REDIS_INFO.getPort());
    }
}
