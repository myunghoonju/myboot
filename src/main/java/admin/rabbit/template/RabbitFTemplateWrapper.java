package admin.rabbit.template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpTimeoutException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static admin.config.amqp.exchange.TopicExchangeConfig.TOPIC_EXCHANGE_NAME;
import static java.lang.Boolean.TRUE;

@Slf4j
@Service
public class RabbitFTemplateWrapper {

    private final RabbitTemplate firstRabbit;

    public RabbitFTemplateWrapper(RabbitTemplate firstRabbit) {
        this.firstRabbit = firstRabbit;
    }

    public void sendMsg(String key, String msg) {
        try {
            boolean isSent = sendAndConfirm(key, msg);
            log.info("sent result {}", isSent);
        } catch (AmqpTimeoutException e) {
            log.error("sent failed {}", e.getMessage());
        }

    }

    private boolean sendAndConfirm(String key, String msg) {
        return TRUE.equals(
                firstRabbit.invoke(ops -> {
                    ops.convertAndSend(TOPIC_EXCHANGE_NAME, key, msg);
                    return ops.waitForConfirms(1000);
                })
        );
    }
}
