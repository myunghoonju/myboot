package admin.config.amqp.exchange;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {

    public static final String TOPIC_EXCHANGE_NAME = "x.test-topic";
    public static final String TOPIC_EXCHANGE_NAME_SEC = "x.test-topic-sec";

    public static final String BINDING_PATTERN = "*.important";
    public static final String BINDING_PATTERN_SEC = "*.ordinary.*";

    @Bean
    public TopicExchange topicExchange(AmqpAdmin firstAmqpAdmin) {
        TopicExchange topicExchange = ExchangeBuilder.topicExchange(TOPIC_EXCHANGE_NAME)
                                                     .build();
        topicExchange.setAdminsThatShouldDeclare(firstAmqpAdmin);

        return topicExchange;
    }

    @Bean
    public TopicExchange topicExchangeSec(AmqpAdmin secondAmqpAdmin) {
        TopicExchange topicExchange = ExchangeBuilder.topicExchange(TOPIC_EXCHANGE_NAME_SEC)
                                                     .build();
        topicExchange.setAdminsThatShouldDeclare(secondAmqpAdmin);

        return topicExchange;
    }

    @Bean
    public Declarables topicBindings(AmqpAdmin firstAmqpAdmin,
                                     AmqpAdmin secondAmqpAdmin,
                                     TopicExchange topicExchange,
                                     TopicExchange topicExchangeSec,
                                     Queue testQueue,
                                     Queue testQueueSec) {
        return new Declarables(getDeclarable(firstAmqpAdmin,
                                             testQueue,
                                             topicExchange,
                                             BINDING_PATTERN),
                               getDeclarable(secondAmqpAdmin,
                                             testQueueSec,
                                             topicExchangeSec,
                                             BINDING_PATTERN_SEC));
    }

    private Declarable getDeclarable(AmqpAdmin amqpAdmin,
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
