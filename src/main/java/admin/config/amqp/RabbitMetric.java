package admin.config.amqp;

import com.codahale.metrics.MetricRegistry;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.impl.StandardMetricsCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

import javax.annotation.PostConstruct;

@Slf4j
public class RabbitMetric {

    private final MetricRegistry registry;

    public RabbitMetric(MetricRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    public void initMetric() {
        CachingConnectionFactory cachingConnectionFactoryFactory = RabbitConfig.getCachingConnectionFactoryFactory();
        ConnectionFactory rabbitConnectionFactory = cachingConnectionFactoryFactory.getRabbitConnectionFactory();
        StandardMetricsCollector metrics = new StandardMetricsCollector(registry);
        rabbitConnectionFactory.setMetricsCollector(metrics);
    }
}
