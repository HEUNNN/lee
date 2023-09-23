package ex.LettuceExample.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisServiceTest {

    @Autowired
    public RedisService redisService;

    @Test
    public void setTest() {
        redisService.setVal(5, "key1", "val1");
    }

}