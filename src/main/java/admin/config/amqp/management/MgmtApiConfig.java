package admin.config.amqp.management;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Configuration
public class MgmtApiConfig {

    @Bean
    public HttpClient amqpHttpClient() {
        return HttpClient.newBuilder()
                         .connectTimeout(Duration.of(2L, ChronoUnit.SECONDS))
                         .build();
    }

    public static String localAuth() {
        String credential = "guest:guest";
        return "Basic " + Base64.getEncoder().encodeToString(credential.getBytes());
    }
}
