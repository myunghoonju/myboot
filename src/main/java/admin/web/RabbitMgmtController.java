package admin.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static admin.config.amqp.management.MgmtApiConfig.localAuth;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
public class RabbitMgmtController {

    private final HttpClient amqpHttpClient;

    public RabbitMgmtController(HttpClient amqpHttpClient) {
        this.amqpHttpClient = amqpHttpClient;
    }

    @GetMapping("/amqp/queues")
    public String getQueues() throws URISyntaxException,
                                                   IOException,
                                                   InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                                     .GET()
                                     .uri(new URI("http://localhost:15672/api/queues"))
                                     .header(AUTHORIZATION, localAuth())
                                     .build();
        HttpResponse<String> response = amqpHttpClient.send(req, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
