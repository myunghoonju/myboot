package admin.config.amqp;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {

    public static final String TOPIC_EXCHANGE_NAME = "x.test-topic";
    public static final String TOPIC_EXCHANGE_NAME_SEC = "x.test-topic-sec";
    public static final String BINDING_PATTERN = "*.important.*";
    public static final String BINDING_PATTERN_SEC = "*.ordinary.*";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME, true, false);
    }

    @Bean
    public TopicExchange topicExchangeSec() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME_SEC, true, false);
    }

    @Bean
    public Declarables topicBindings(TopicExchange topicExchange,
                                     TopicExchange topicExchangeSec,
                                     Queue testQueue,
                                     Queue testQueueSec) {

        return new Declarables(BindingBuilder
                                       .bind(testQueue)
                                       .to(topicExchange)
                                       .with(BINDING_PATTERN),
                               BindingBuilder
                                       .bind(testQueueSec)
                                       .to(topicExchangeSec)
                                       .with(BINDING_PATTERN_SEC));
    }
}
