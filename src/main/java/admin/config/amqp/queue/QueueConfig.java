package admin.config.amqp.queue;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    private static final String QUEUE_NAME = "q.topic.test";

    @Bean
    public Queue testQueue(AmqpAdmin firstAmqpAdmin) {
        Queue testQueue = QueueBuilder.durable(QUEUE_NAME)
                                      .quorum()
                                      .build();
        testQueue.setAdminsThatShouldDeclare(firstAmqpAdmin);

        return testQueue;
    }
}
