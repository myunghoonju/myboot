package admin.rabbit.template;

import admin.config.amqp.exchange.TopicExchangeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.AmqpTimeoutException;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static admin.config.amqp.exchange.TopicExchangeConfig.TOPIC_EXCHANGE_NAME;
import static java.lang.Boolean.TRUE;

@Slf4j
@Service
public class RabbitTemplateWrapper {

    private static final String FANOUT_EXCHANGE_NAME = "amq.fanout";

    private final RabbitTemplate firstRabbit;

    private final AmqpAdmin firstAmqpAdmin;

    public RabbitTemplateWrapper(RabbitTemplate firstRabbit, AmqpAdmin firstAmqpAdmin) {
        this.firstRabbit = firstRabbit;
        this.firstAmqpAdmin = firstAmqpAdmin;
    }

    public void sendMsg(String key, String msg) {
        try {
            boolean isSent = sendAndConfirm(key, msg);
            log.info("sent result {}", isSent);
        } catch (AmqpException e) {
            log.error("sent failed {}", e.getMessage());
            throw new AmqpException(e.getMessage());
        }

    }

    public void sendMsg2(String msg) {
        try {
            boolean isSent = sendAndConfirm2(msg);
            log.info("sent2 result {}", isSent);
        } catch (AmqpTimeoutException e) {
            log.error("sent2 failed {}", e.getMessage());
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

    private boolean sendAndConfirm2(String msg) {
        return TRUE.equals(
                firstRabbit.invoke(ops -> {
                    ops.convertAndSend(FANOUT_EXCHANGE_NAME, null, msg);
                    return ops.waitForConfirms(1000);
                })
        );
    }
    
    public void buildTopicExchange() {
        Exchange build = ExchangeBuilder.topicExchange(TopicExchangeConfig.TOPIC_EXCHANGE_NAME).build();
        firstAmqpAdmin.declareExchange(build);
    }
}
