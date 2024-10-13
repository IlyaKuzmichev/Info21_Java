package edu.school21.info21;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class Info21ApplicationTests {

    @Test
    void contextLoads() {
    }

}
