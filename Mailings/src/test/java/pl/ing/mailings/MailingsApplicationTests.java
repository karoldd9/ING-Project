package pl.ing.mailings;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailingsApplicationTests {

    @Test
    void contextLoads() {
        assertEquals("hello", new String("hello"));
    }

}
