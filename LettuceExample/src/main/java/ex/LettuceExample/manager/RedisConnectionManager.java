package ex.LettuceExample.manager;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Service;

@Service
public class RedisConnectionManager {
    private static LettuceConnectionFactory lettuceConnectionFactory;

    public static LettuceConnectionFactory getInstance() {
        return lettuceConnectionFactory;
    }
}
