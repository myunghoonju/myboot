package admin.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * this configures host, port of rabbit and bind to a template
 * only connection settings.
 * */
@Configuration
public class RabbitConfig {

    /**
     * inject server info beans
     *
     * */

    @Bean
    public ConnectionFactory firstConnection() {
        CachingConnectionFactory conn = new CachingConnectionFactory();
        conn.setHost("localhost");
        conn.setPort(5672);
        conn.setUsername("guest");
        conn.setPassword("guest");
        return conn;
    }

    @Bean
    public ConnectionFactory secondConnection() {
        CachingConnectionFactory conn = new CachingConnectionFactory();
        conn.setHost("localhost");
        conn.setPort(5672);
        conn.setUsername("guest");
        conn.setPassword("guest");
        return conn;
    }

    @Bean
    public RabbitTemplate firstRabbit() {
        return new RabbitTemplate(firstConnection());
    }
    @Bean
    public RabbitTemplate secondRabbit() {
        return new RabbitTemplate(secondConnection());
    }
}
