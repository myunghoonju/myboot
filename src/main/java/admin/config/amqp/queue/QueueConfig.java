package admin.config.amqp.queue;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    private static final String QUEUE_NAME = "q.topic.test";
    private static final String QUEUE_NAME_SEC = "q.test-sec";
    private static final String FANOUT_QUEUE = "q.blocking-queue";

    @Bean
    public Queue testQueue(AmqpAdmin firstAmqpAdmin) {
        Queue testQueue = QueueBuilder.durable(QUEUE_NAME)
                                      .quorum()
                                      .build();
        testQueue.setAdminsThatShouldDeclare(firstAmqpAdmin);

        return testQueue;
    }

    @Bean
    public Queue testQueueSec(AmqpAdmin secondAmqpAdmin) {
        Queue test2 = QueueBuilder.durable(QUEUE_NAME_SEC)
                                  .quorum()
                                  .build();
        test2.setAdminsThatShouldDeclare(secondAmqpAdmin);

        return test2;
    }

    @Bean
    public Queue fanoutQueue(AmqpAdmin firstAmqpAdmin) {
        Queue testQueue = QueueBuilder.nonDurable(FANOUT_QUEUE)
                //.quorum() > can't nonDurable
                .build();

        testQueue.setAdminsThatShouldDeclare(firstAmqpAdmin);

        return testQueue;
    }
}
