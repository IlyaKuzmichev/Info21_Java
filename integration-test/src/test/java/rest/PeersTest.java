package rest;

import config.TestConfig;
import edu.school21.info21.dto.PeersDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestConfig.class)
public class PeersTest {
    @LocalServerPort
    private int port;  // Динамический порт

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void testGetPeers() {
        // Формируем URL с динамическим портом
        String url = "http://localhost:" + port + "/api/v1/peers";

        // Выполняем GET запрос к контроллеру
        ResponseEntity<List<PeersDTO>> responseEntity = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        // Проверка статуса ответа
        assertEquals(200, responseEntity.getStatusCode().value());

        // Проверка содержимого ответа
        var peers = responseEntity.getBody();
        assert peers != null && !peers.isEmpty();
        assertEquals("elevante", peers.getFirst().getNickname());
    }

}
