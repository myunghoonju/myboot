package admin.config.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    public static final String QUEUE_NAME = "q.test";
    public static final String QUEUE_NAME_SEC = "q.test-sec";

    @Bean
    public Queue testQueue() {
     return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Queue testQueueSec() {
     return new Queue(QUEUE_NAME_SEC, true);
    }
}
