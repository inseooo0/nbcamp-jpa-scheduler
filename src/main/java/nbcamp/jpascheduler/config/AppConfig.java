package nbcamp.jpascheduler.config;

import nbcamp.jpascheduler.jwt.JwtUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder.build();
    }
}
