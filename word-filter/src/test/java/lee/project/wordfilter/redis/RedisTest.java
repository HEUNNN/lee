package lee.project.wordfilter.redis;

import lee.project.wordfilter.service.RedisService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@SpringBootTest
class RedisTest {
    private static final int KEY_SPACE_FOR_TEST = 0;
    @Autowired
    LettuceConnectionFactory lettuceConnectionFactory;

    @Autowired
    RedisService redisService;

    private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @AfterEach
    public void clearTestKeySpace() {
        RedisConnection client = lettuceConnectionFactory.getConnection();
        try {
            client.select(KEY_SPACE_FOR_TEST);
            client.flushDb();
        } catch (Exception e) {
            logger.error("clear test key space exception, msg = " + e.getMessage());
        } finally {
            client.close();
        }
    }


    @Test
    void redisTransactionTest() {
        RedisConnection conn = lettuceConnectionFactory.getConnection();
        try {
            conn.multi();
            conn.select(KEY_SPACE_FOR_TEST);
            conn.set("key1".getBytes(), "val1".getBytes());
            conn.set("key2".getBytes(), "val2".getBytes());
            conn.exec();
        } catch (Exception e) {
            logger.error("redis transaction test exception, msg = " + e.getMessage());
        }
    }

    @Test
    void pipelineTest() {
        redisService.pipeline();
    }
}