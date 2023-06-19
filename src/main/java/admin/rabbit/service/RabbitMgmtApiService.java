package admin.rabbit.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static admin.config.amqp.management.MgmtApiConfig.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Service
public class RabbitMgmtApiService {

    private final AmqpAdmin firstAmqpAdmin;
    private final HttpClient amqpHttpClient;
    private static final ObjectMapper mapper = new ObjectMapper();

    public RabbitMgmtApiService(AmqpAdmin firstAmqpAdmin,
                                HttpClient amqpHttpClient) {
        this.firstAmqpAdmin = firstAmqpAdmin;
        this.amqpHttpClient = amqpHttpClient;
    }

    public String getName() {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(API_QUEUE_LIST))
                    .header(AUTHORIZATION, localAuth())
                    .build();
            HttpResponse<String> response = amqpHttpClient.send(req, HttpResponse.BodyHandlers.ofString());
            JsonNode jsonNode = mapper.readTree(response.body());
            return jsonNode.path(0).path("name").asText();

        } catch (Exception e) {
            log.error("RabbitMgmtApiService.getQueues ", e);
        }

        return null;
    }

    public List<Object> getTopology() {
        List<Object> res = new ArrayList<>();
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(API_BIND_LIST))
                    .header(AUTHORIZATION, localAuth())
                    .build();
            HttpResponse<String> response = amqpHttpClient.send(req, HttpResponse.BodyHandlers.ofString());
            res = Arrays.asList(mapper.readValue(response.body(), Object[].class));
        } catch (Exception e) {
            log.error("RabbitMgmtApiService.getQueues ", e);
        }

        return res;
    }

    public boolean delete(String name) {
        return firstAmqpAdmin.deleteQueue(name);
    }
}
