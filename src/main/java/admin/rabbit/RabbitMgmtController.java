package admin.rabbit;

import admin.rabbit.service.RabbitMgmtApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static admin.config.amqp.management.MgmtApiConfig.API_BIND_LIST;
import static admin.config.amqp.management.MgmtApiConfig.API_QUEUE_LIST;

@RestController
public class RabbitMgmtController {

    private RabbitMgmtApiService service;

    public RabbitMgmtController(RabbitMgmtApiService service) {
        this.service = service;
    }

    @GetMapping("/amqp/queues")
    public List<Object> getQueues() {
        return service.getQueues(API_QUEUE_LIST);
    }

    @GetMapping("/amqp/bindings")
    public List<Object> getBindings() {
        return service.getQueues(API_BIND_LIST);
    }
}
