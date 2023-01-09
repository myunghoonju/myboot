package admin.rabbit.template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static admin.config.amqp.exchange.TopicExchangeConfig.TOPIC_EXCHANGE_NAME;

@Slf4j
@Service
public class RabbitFTemplateWrapper {

    private final RabbitTemplate firstRabbit;

    public RabbitFTemplateWrapper(RabbitTemplate firstRabbit) {
        this.firstRabbit = firstRabbit;
    }

    public void sendMsg(String key, String msg) {
        boolean isSent = sendAndConfirm(key, msg);
        log.info("sent result {}", isSent);
    }

    private boolean sendAndConfirm(String key, String msg) {
        return Boolean.TRUE.equals(firstRabbit.invoke(ops -> {
            ops.convertAndSend(TOPIC_EXCHANGE_NAME, key, msg);
            return ops.waitForConfirms(1000);
        }));
    }

}
