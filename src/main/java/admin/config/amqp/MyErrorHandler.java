package admin.config.amqp;

import admin.config.amqp.exchange.TopicExchangeConfig;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ShutdownSignalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

import static admin.config.amqp.exchange.TopicExchangeConfig.BINDING_PATTERN;
import static admin.config.amqp.exchange.TopicExchangeConfig.TOPIC_EXCHANGE_NAME;

@Slf4j
@ControllerAdvice
public class MyErrorHandler {

    private final AmqpAdmin firstAmqpAdmin;
    private final Queue testQueue;
    private final TopicExchange topicExchange;
    private final ConnectionFactory firstConnection;
    @Autowired
    public Declarables topicBindings;

    public MyErrorHandler(AmqpAdmin firstAmqpAdmin,
                          Queue testQueue,
                          TopicExchange topicExchange,
                          ConnectionFactory firstConnection) {
        this.firstAmqpAdmin = firstAmqpAdmin;
        this.testQueue = testQueue;
        this.topicExchange = topicExchange;
        this.firstConnection = firstConnection;
    }

    @ExceptionHandler(AmqpException.class)
    public ResponseEntity<String> handlerAmqpException(AmqpException e) {
        firstConnection.resetConnection();
        return new ResponseEntity<>("Amqp Error " + firstConnection.getHost() + e.getMessage(), HttpStatus.OK);
    }
}
