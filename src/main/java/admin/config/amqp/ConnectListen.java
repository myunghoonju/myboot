package admin.config.amqp;

import com.rabbitmq.client.ShutdownSignalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConnectListen implements ConnectionListener {

    @Override
    public void onCreate(Connection connection) {
        log.info("onCreate {}", connection.getDelegate());
    }

    @Override
    public void onClose(Connection connection) {
        log.info("onClose {}", connection.getDelegate());
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        log.info("onShutDown {}", signal.getReason());
        throw new RuntimeException(signal.getMessage());
    }

    @Override
    public void onFailed(Exception exception) {
        log.info("onFailed {}", exception.getMessage());
        throw new RuntimeException(exception.getMessage());
    }
}
