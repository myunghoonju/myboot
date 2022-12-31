package admin.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RabbitController {

    private final RabbitTemplate template;

    @GetMapping("/test")
    public String send() {
        template.convertAndSend("topic-exchange", "test-routing.key", "121212");
        return "ok. done";
    }
}
