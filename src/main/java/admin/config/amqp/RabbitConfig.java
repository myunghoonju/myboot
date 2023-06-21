package admin.config.amqp;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.rabbit.connection.CachingConnectionFactory.CacheMode.CONNECTION;
import static org.springframework.amqp.rabbit.connection.CachingConnectionFactory.ConfirmType.SIMPLE;

@Configuration
@EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
public class RabbitConfig {

    private final ConnectListen connectListen;

    public RabbitConfig(ConnectListen connectListen) {
        this.connectListen = connectListen;
    }

    public static CachingConnectionFactory getCachingConnectionFactoryFactory() {
       return new CachingConnectionFactory();
    }

    @Bean
    public ConnectionFactory firstConnection() {
        CachingConnectionFactory conn = getCachingConnectionFactoryFactory();
        conn.setHost("localhost");
        conn.setPort(5672);
        conn.setUsername("guest");
        conn.setPassword("guest");
        conn.addConnectionListener(connectListen);
        conn.setPublisherConfirmType(SIMPLE);
        conn.setConnectionTimeout(1000);
        conn.setRequestedHeartBeat(5);
        conn.setCacheMode(CONNECTION);

        return conn;
    }

    @Bean
    public RabbitTemplate firstRabbit() {
        RabbitTemplate firstRabbit = new RabbitTemplate(firstConnection());
        firstRabbit.setMessageConverter(converter());

        return firstRabbit;
    }

    @Bean
    public AmqpAdmin firstAmqpAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(firstRabbit());
        rabbitAdmin.setRedeclareManualDeclarations(true);

        return rabbitAdmin;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
