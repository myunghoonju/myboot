package admin.rabbit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static admin.config.amqp.management.MgmtApiConfig.localAuth;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Service
public class RabbitMgmtApiService {

    private final HttpClient amqpHttpClient;
    private static final ObjectMapper mapper = new ObjectMapper();

    public RabbitMgmtApiService(HttpClient amqpHttpClient) {
        this.amqpHttpClient = amqpHttpClient;
    }

    public List<Object> getQueues(String uri) {
        List<Object> res = new ArrayList<>();
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(uri))
                    .header(AUTHORIZATION, localAuth())
                    .build();
            HttpResponse<String> response = amqpHttpClient.send(req, HttpResponse.BodyHandlers.ofString());
            res = Arrays.asList(mapper.readValue(response.body(), Object[].class));
        } catch (Exception e) {
            log.error("RabbitMgmtApiService.getQueues ", e);
        }

        return res;
    }
}
