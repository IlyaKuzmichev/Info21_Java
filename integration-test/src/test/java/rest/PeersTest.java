package rest;

import config.TestConfig;
import edu.school21.info21.dto.PeersDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TestConfig.class)
public class PeersTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void testGetPeers() {
        String url = "http://localhost:8080/api/v1/peers";

        ResponseEntity<List<PeersDTO>> responseEntity = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        assertEquals(200, responseEntity.getStatusCode().value());

        var peers = responseEntity.getBody();
        assert peers != null && !peers.isEmpty();
        assertEquals("elevante", peers.getFirst().getNickname());
    }

}
