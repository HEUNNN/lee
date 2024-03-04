package lee.project.wordfilter.service;

import lee.project.wordfilter.constant.RedisConstant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@SpringBootTest
class RedisServiceTest {
    private static final int KEY_SPACE_FOR_TEST = 0;
    private static final String BAN_WORD_1 = "fuck";
    private static final String BAN_WORD_2 = "c8";

    @Autowired
    LettuceConnectionFactory lettuceConnectionFactory;
    @Autowired
    RedisService redisService;

    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

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
    void hSet() {
        redisService.hSet(KEY_SPACE_FOR_TEST, RedisConstant.KEY_FOR_BAN_WORD, RedisConstant.FIELD_FOR_COMMON_PROJECT, BAN_WORD_1);
        Assertions.assertThat(redisService.hGet(KEY_SPACE_FOR_TEST, RedisConstant.KEY_FOR_BAN_WORD, RedisConstant.FIELD_FOR_COMMON_PROJECT)).isEqualTo(BAN_WORD_1);
    }
}