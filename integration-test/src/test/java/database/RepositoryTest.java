package database;

import config.TestConfig;
import edu.school21.info21.repositories.PeersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = TestConfig.class)
public class RepositoryTest {

    @Autowired
    private PeersRepository peerRepository;

    @Test
    void test() {
        assertFalse(peerRepository.findAll().isEmpty());
    }
}
