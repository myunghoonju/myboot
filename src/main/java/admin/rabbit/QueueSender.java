package admin.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueSender {

    private final Queue queue;
    private final RabbitTemplate rabbitTemplate;

    public void send(String order) {
        rabbitTemplate.convertAndSend(queue.getName(), order);
    }
}
