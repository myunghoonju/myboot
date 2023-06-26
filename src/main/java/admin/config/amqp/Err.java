package admin.config.amqp;

import com.rabbitmq.client.MissedHeartbeatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.SocketTimeoutException;

@Slf4j
@ControllerAdvice
public class Err {

    @ExceptionHandler(AmqpException.class)
    ResponseEntity<Boolean> handle(AmqpException e) {
        log.error("aldskfjlaksdfjlaksjfdkl;asfd");


        return ResponseEntity.ok(false);
    }

    @ExceptionHandler(SocketTimeoutException.class)
    ResponseEntity<Boolean> handle(SocketTimeoutException e) {
        log.error("aldskfjlaksdfjlaksjfdkl;asfd");
        log.error("aldskfjlaksdfjlaksjfdkl;asfd");

        throw new RuntimeException(e.getMessage());
    }

    @ExceptionHandler(MissedHeartbeatException.class)
    ResponseEntity<Boolean> handlde(MissedHeartbeatException e) {
        log.error("aldskfjlaksdfjlaksjfdkl;asfd");
        log.error("aldskfjlaksdfjlaksjfdkl;asfd");
        log.error("aldskfjlaksdfjlaksjfdkl;asfd");

        throw new RuntimeException(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Boolean> handlde(Exception e) {
        log.error("catch all");

        throw new RuntimeException(e.getMessage());
    }


}
