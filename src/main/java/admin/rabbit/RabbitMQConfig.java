package admin.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// https://medium.com/javarevisited/first-steps-with-rabbitmq-and-spring-boot-81d293554703
@Configuration
public class RabbitMQConfig {

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public Queue testeQueue() {
        return new Queue("testt", false, false, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Binding testeBinding(Queue testeQueue, TopicExchange exchange) {
        return BindingBuilder.bind(testeQueue).to(exchange).with("test-routing.#");
    }
}