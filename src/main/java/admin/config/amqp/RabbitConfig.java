package admin.config.amqp;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
public class RabbitConfig {

    //TODO:: inject server info beans

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
        conn.setPort(5673);
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

    @Bean
    public AmqpAdmin firstAmqpAdmin() {
        RabbitAdmin firstAdmin = new RabbitAdmin(firstRabbit());
        return firstAdmin;
    }

    @Bean
    public AmqpAdmin secondAmqpAdmin() {
        RabbitAdmin secRabbitAdmin = new RabbitAdmin(secondRabbit());
        return secRabbitAdmin;
    }
}
