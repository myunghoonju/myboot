package admin.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RabbitController {

    private final QueueSender sender;
    private final RabbitTemplate template;
/*

    @GetMapping("/test")
    public String send() {
        sender.send("send test message from controller");
        return "ok";
    }
*/


    @GetMapping("/test")
    public String send() throws JsonProcessingException {
        template.convertAndSend("topic-exchange", "test-routing.key", "121212");
        return "ok. done";
    }
}
