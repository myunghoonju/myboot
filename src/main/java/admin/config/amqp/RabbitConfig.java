package admin.config.amqp;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.rabbit.connection.CachingConnectionFactory.ConfirmType.SIMPLE;

@Configuration
@EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
public class RabbitConfig {

    public static CachingConnectionFactory getCachingConnectionFactoryFactory() {
       return new CachingConnectionFactory();
    }

    @Bean
    public ConnectionFactory firstConnection() {
        CachingConnectionFactory conn = getCachingConnectionFactoryFactory();
        conn.setHost("localhost");
        conn.setPort(5672);
        conn.setUsername("guest");
        conn.setPassword("guest");
        conn.setPublisherConfirmType(SIMPLE); // ?
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
        RabbitTemplate firstRabbit = new RabbitTemplate(firstConnection());
        firstRabbit.setMessageConverter(converter());

        return firstRabbit;
    }
    @Bean
    public RabbitTemplate secondRabbit() {
        RabbitTemplate secondRabbit = new RabbitTemplate(secondConnection());
        secondRabbit.setMessageConverter(converter());

        return secondRabbit;
    }

    @Bean
    public AmqpAdmin firstAmqpAdmin() {
        return new RabbitAdmin(firstRabbit());
    }

    @Bean
    public AmqpAdmin secondAmqpAdmin() {
        return new RabbitAdmin(secondRabbit());
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
