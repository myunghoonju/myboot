package admin.rabbit;

import admin.config.amqp.TopicExchangeConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static admin.config.amqp.TopicExchangeConfig.TOPIC_EXCHANGE_NAME;

@Service
public class RabbitFTemplateWrapper {

    private final RabbitTemplate firstRabbit;

    public RabbitFTemplateWrapper(RabbitTemplate firstRabbit) {
        this.firstRabbit = firstRabbit;
    }

    public void sendMsg(String key) {
        firstRabbit.convertAndSend(TOPIC_EXCHANGE_NAME, key, "messgesA");
    }
}
