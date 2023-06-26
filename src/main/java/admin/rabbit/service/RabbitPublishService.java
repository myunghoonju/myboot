package admin.rabbit.service;

import admin.rabbit.template.RabbitTemplateWrapper;
import org.springframework.stereotype.Service;

@Service
public class RabbitPublishService {

    private final RabbitTemplateWrapper rabbitTemplateWrapper;

    public RabbitPublishService(RabbitTemplateWrapper rabbitTemplateWrapper) {
        this.rabbitTemplateWrapper = rabbitTemplateWrapper;
    }

    public void sendMsg(String key, String msg) {
       // rabbitTemplateWrapper.buildTopicExchange();
        rabbitTemplateWrapper.sendMsg(key, msg);
    }
}
