package lee.project.Connect.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisServiceTest {

    @Autowired
    public RedisService redisService;

    @Test
    public void setTest() {
        redisService.setValue(1, "hello", "dd");
    }

    @Test
    public void hsetTest() {
        redisService.hset(1, "file_del_crawler", "start", "y");
        redisService.hset(1, "file_del_crawler", "end", "y");
        redisService.hset(1, "file_del_crawler_erp", "start", "y");
        redisService.hset(1, "file_del_crawler_erp", "exception", "y");

        System.out.println(redisService.hget(1, "file_del_crawler", "start"));
        System.out.println(redisService.hget(1, "file_del_crawler", "end"));
        System.out.println(redisService.hget(1, "file_del_crawler_erp", "start"));
        String res = redisService.hget(1, "file_del_crawler_ecm", "start");
        System.out.println(res);
    }
}