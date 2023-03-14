package admin.rabbit.service;

import admin.rabbit.template.RabbitFTemplateWrapper;
import admin.rabbit.template.RabbitSTemplateWrapper;
import org.springframework.stereotype.Service;

@Service
public class RabbitPublishService {

    private final RabbitFTemplateWrapper rabbitFTemplateWrapper;
    private final RabbitSTemplateWrapper rabbitSTemplateWrapper;

    public RabbitPublishService(RabbitFTemplateWrapper rabbitFTemplateWrapper,
                                RabbitSTemplateWrapper rabbitSTemplateWrapper) {
        this.rabbitFTemplateWrapper = rabbitFTemplateWrapper;
        this.rabbitSTemplateWrapper = rabbitSTemplateWrapper;
    }

    public void sendMsg(String key, String msg) {
        rabbitFTemplateWrapper.sendMsg(key, msg);
    }

    public void sendMsg2(String msg) {
        rabbitFTemplateWrapper.sendMsg2(msg);
    }

}
