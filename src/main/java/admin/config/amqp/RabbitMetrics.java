package admin.config.amqp;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.impl.StandardMetricsCollector;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RabbitMetrics {

    public static void info() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        StandardMetricsCollector metrics = new StandardMetricsCollector();
        System.out.println("------" + metrics.getChannels().getCount());
        System.out.println("------" + metrics.getConnections().getCount());
    }
}
