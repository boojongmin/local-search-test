package boojongmin.localsearch.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ComponentConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(1))
                .setReadTimeout(Duration.ofSeconds(2))
                .build();
        return restTemplate;

    }

    @Bean
    public BoundZSetOperations<String, String> locationSearchZSetOperations(RedisOperations<String, String> operations) {
        return operations.boundZSetOps("location-search");
    }
}
