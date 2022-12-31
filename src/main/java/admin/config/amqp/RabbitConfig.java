package admin.config.amqp;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
        conn.setPort(5672);
        conn.setUsername("guest");
        conn.setPassword("guest");
        return conn;
    }

    @Bean
    public RabbitTemplate firstRabbit() {
        RabbitTemplate firTemp = new RabbitTemplate(firstConnection());
        firTemp.setMessageConverter(converter());

        return firTemp;
    }
    @Bean
    public RabbitTemplate secondRabbit() {
        RabbitTemplate secTemp = new RabbitTemplate(secondConnection());
        secTemp.setMessageConverter(converter());

        return secTemp;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
