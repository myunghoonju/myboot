package admin.rabbit.template;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static admin.config.amqp.exchange.TopicExchangeConfig.TOPIC_EXCHANGE_NAME_SEC;

@Service
public class RabbitSTemplateWrapper {

    private final RabbitTemplate secondRabbit;

    public RabbitSTemplateWrapper(RabbitTemplate secondRabbit) {
        this.secondRabbit = secondRabbit;
    }

    public void sendMsgSec(String key, String msg) {
        secondRabbit.convertAndSend(TOPIC_EXCHANGE_NAME_SEC, key, msg);
    }
}
