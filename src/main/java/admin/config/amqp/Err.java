package admin.config.amqp;

import org.springframework.amqp.AmqpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.SocketTimeoutException;

@ControllerAdvice
public class Err {

    @ExceptionHandler(AmqpException.class)
    ResponseEntity<String> handle(AmqpException e) {
        return ResponseEntity.ok(e.getMessage());
    }

    @ExceptionHandler(SocketTimeoutException.class)
    ResponseEntity<String> handleS(SocketTimeoutException e) {
        return ResponseEntity.ok(e.getMessage());
    }
}
