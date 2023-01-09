package admin.rabbit.template;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static admin.config.amqp.exchange.TopicExchangeConfig.TOPIC_EXCHANGE_NAME;

@Service
public class RabbitFTemplateWrapper {

    private final RabbitTemplate firstRabbit;

    public RabbitFTemplateWrapper(RabbitTemplate firstRabbit) {
        this.firstRabbit = firstRabbit;
    }

    public void sendMsg(String key, String msg) {
        Boolean isSent = firstRabbit.invoke(ops -> {
            ops.convertAndSend(TOPIC_EXCHANGE_NAME, key, msg);
            return ops.waitForConfirms(1000);
        });

        System.out.println("SENT RESULT:: " + isSent);
    }
}
