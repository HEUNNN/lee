package ex.LettuceExample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    public LettuceConnectionFactory lettuceConnectionFactory;

    public void setVal(int dbNum, String key, String val) {
        RedisConnection connection = lettuceConnectionFactory.getConnection();
        try {
            connection.select(dbNum);
            connection.set(key.getBytes(), val.getBytes());
        } catch (Exception e) {
            System.out.println("Reis " + dbNum + "?? (" + key + ", " + val + ") ?? ? ??? ???????.");
        }
    }
}
