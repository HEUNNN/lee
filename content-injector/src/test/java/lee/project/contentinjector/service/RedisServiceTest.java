package lee.project.contentinjector.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisServiceTest {

    @Autowired
    RedisService service;

    @Test
    public void test() {
        service.connectTest();
    }

}