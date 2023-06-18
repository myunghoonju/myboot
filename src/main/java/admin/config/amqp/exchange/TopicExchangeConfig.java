package admin.config.amqp.exchange;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {

    public static final String TOPIC_EXCHANGE_NAME = "x.test-topic";
    public static final String BINDING_PATTERN = "*.important";
    @Autowired
    public AmqpAdmin firstAmqpAdmin;

    @Bean
    public TopicExchange topicExchange() {
        TopicExchange topicExchange = ExchangeBuilder.topicExchange(TOPIC_EXCHANGE_NAME)
                                                     .build();
        topicExchange.setAdminsThatShouldDeclare(firstAmqpAdmin);

        return topicExchange;
    }

    @Bean
    public Declarables topicBindings(AmqpAdmin firstAmqpAdmin,
                                     TopicExchange topicExchange,
                                     Queue testQueue) {
        return new Declarables(getDeclarable(firstAmqpAdmin,
                                             testQueue,
                                             topicExchange,
                                             BINDING_PATTERN));
    }

    public static Declarable getDeclarable(AmqpAdmin amqpAdmin,
                                     Queue queue,
                                     TopicExchange exchange,
                                     String pattern) {
        Binding binding = BindingBuilder.bind(queue)
                                        .to(exchange)
                                        .with(pattern);
        binding.setAdminsThatShouldDeclare(amqpAdmin);

        return binding;
    }
}
