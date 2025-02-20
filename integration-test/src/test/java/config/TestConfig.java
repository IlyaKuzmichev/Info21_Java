package config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
//@EnableJpaRepositories(basePackages = "edu.school21.info21.repositories")
//@EntityScan(basePackages = "edu.school21.info21..model")
@ComponentScan(basePackages = "edu.school21.info21")
@TestPropertySource(locations = "classpath:application-integration.yml")
public class TestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
