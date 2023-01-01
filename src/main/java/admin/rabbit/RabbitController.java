package admin.rabbit;

import admin.rabbit.service.RabbitPublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class RabbitController {

    private final RabbitPublishService rabbitPublishService;

    @GetMapping("/test")
    public String send() {
        rabbitPublishService.sendMsg("a.important", "message from test");

        return "done";
    }
}
