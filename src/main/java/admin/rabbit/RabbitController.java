package admin.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RabbitController {

    private final RabbitFTemplateWrapper rabbitFTemplateWrapper;
    private final RabbitSTemplateWrapper rabbitSTemplateWrapper;

    @GetMapping("/test")
    public String send() {
        rabbitFTemplateWrapper.sendMsg("a.important.a");
        rabbitSTemplateWrapper.sendMsgSec("b.ordinary.b");

        return "done";
    }
}
