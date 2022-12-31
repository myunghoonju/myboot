package admin.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CheckConsumption {

    @RabbitListener(queues = {"${queue.fname}"})
    public void receive(@Payload String fileBody) {
        log.info("Message fir " + fileBody);
    }

    @RabbitListener(queues = {"${queue.sname}"})
    public void receivesec(@Payload String fileBody) {
        log.info("Message sec " + fileBody);
    }
}
