package admin.config.amqp.exchange;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanOutExchangeConfig {

    public static final String FANOUT_EXCHANGE_NAME = "x.test-fanout";

    @Bean
    public FanoutExchange fanoutExchange(AmqpAdmin firstAmqpAdmin) {
        FanoutExchange fanoutExchange = ExchangeBuilder.fanoutExchange(FANOUT_EXCHANGE_NAME)
                                                     .build();
        fanoutExchange.setAdminsThatShouldDeclare(firstAmqpAdmin);

        return fanoutExchange;
    }

    @Bean
    public Declarables fanoutBindings(AmqpAdmin firstAmqpAdmin,
                                     Queue fanoutQueue,
                                     FanoutExchange fanoutExchange) {
        return new Declarables(getDeclarable(firstAmqpAdmin, fanoutQueue, fanoutExchange));
    }

    private Declarable getDeclarable(AmqpAdmin amqpAdmin,
                                     Queue queue,
                                     FanoutExchange exchange) {
        Binding binding = BindingBuilder.bind(queue)
                                        .to(exchange);
        binding.setAdminsThatShouldDeclare(amqpAdmin);

        return binding;
    }
}
