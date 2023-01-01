package admin.config.amqp.queue;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    private static final String QUEUE_NAME = "q.topic.test";
    private static final String QUEUE_NAME_SEC = "q.test-sec";
    private static final String QUEUE_NAME_THIRD = "q.test-third";

    @Bean
    public Queue testQueue(AmqpAdmin firstAmqpAdmin) {
        Queue testQueue = new Queue(QUEUE_NAME, true);
        testQueue.setAdminsThatShouldDeclare(firstAmqpAdmin);

        return testQueue;
    }

    @Bean
    public Queue testQueueSec(AmqpAdmin secondAmqpAdmin) {
        Queue test2 = new Queue(QUEUE_NAME_SEC, true);
        test2.setAdminsThatShouldDeclare(secondAmqpAdmin);
        return test2;
    }

    @Bean
    public Queue testQueueThird(AmqpAdmin secondAmqpAdmin) {
        Queue test3 = new Queue(QUEUE_NAME_THIRD, true);
        test3.setAdminsThatShouldDeclare(secondAmqpAdmin);
        return test3;
    }
}
