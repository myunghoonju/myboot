package admin.rabbit;

import admin.rabbit.service.RabbitMgmtApiService;
import admin.web.dto.Bindings;
import admin.web.dto.XTestFanOutQueue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RabbitMgmtController {

    private RabbitMgmtApiService service;

    public RabbitMgmtController(RabbitMgmtApiService service) {
        this.service = service;
    }

    @GetMapping("/amqp/queues")
    public String getQueues() {
        return service.getName();
    }

    @GetMapping("/amqp/queue/gone")
    public Boolean delete() {
        String name = service.getName();
        return service.delete(name);
    }

    @GetMapping("/amqp/bindings")
    public Bindings getBindings() {
        return Bindings.toRes(service.getTopology());
    }

    @GetMapping("/amqp/bindings/clear")
    public int deleteAll() {
        List<XTestFanOutQueue> topology = service.getTopology();
        return service.deleteAll(topology);
    }
}
