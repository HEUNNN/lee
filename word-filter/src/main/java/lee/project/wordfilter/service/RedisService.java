package lee.project.wordfilter.service;

import co.elastic.clients.elasticsearch._types.InlineScript;
import co.elastic.clients.json.JsonData;
import lee.project.wordfilter.util.ConvertUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RedisService {
    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    LettuceConnectionFactory lettuceConnectionFactory;


    public void set(int keySpace, String key, String value) {
        RedisConnection client = lettuceConnectionFactory.getConnection();
        try {
            client.select(keySpace);
            client.set(key.getBytes(), value.getBytes());
        } catch (Exception e) {
            logger.error("set value error, msg = " + e.getMessage());
        } finally {
            client.close();
        }
    }

    public String get(int keySpace, String key) {
        RedisConnection client = lettuceConnectionFactory.getConnection();
        try {
            client.select(keySpace);
            return ConvertUtil.convertByteArrToString(client.get(key.getBytes()));
        } catch (Exception e) {
            logger.error("get error, msg = " + e.getMessage());
            throw e;
        }
    }

    public void hSet(int keySpace, String key, String field, String value) {
        RedisConnection client = lettuceConnectionFactory.getConnection();
        try {
            client.select(keySpace);
            client.hSet(key.getBytes(), field.getBytes(), value.getBytes());
        } catch (Exception e) {
            logger.error("hash set error, msg = " + e.getMessage());
        } finally {
            client.close();
        }
    }

    public String hGet(int keySpace, String key, String field) {
        RedisConnection client = lettuceConnectionFactory.getConnection();
        try {
            client.select(keySpace);
            return ConvertUtil.convertByteArrToString(client.hGet(key.getBytes(), field.getBytes()));
        } catch (Exception e) {
            logger.error("hash get error, msg = " + e.getMessage());
            throw e;
        }
    }

    public void pipeline() {
        RedisConnection client = lettuceConnectionFactory.getConnection();
        try {
            String hKey = "hashKey";
            client.select(0);
            client.openPipeline();
            for (int i = 0; i < 10; i++) {
                String field = "filed_" + String.valueOf(i);
                Boolean b = client.hSet(hKey.getBytes(), field.getBytes(), String.valueOf(i).getBytes());
                System.out.println(b);
            }
            System.out.println("is pipelined " + client.isPipelined());
            for (int i = 11; i < 21; i++) {
                String field = "filed_" + String.valueOf(i);
                client.hSet(hKey.getBytes(), field.getBytes(), String.valueOf(i).getBytes());
            }
            List<Object> objects = client.closePipeline();
            System.out.println("is pipelined " + client.isPipelined());
        } catch (Exception e) {
            logger.error("hash get error, msg = " + e.getMessage());
            throw e;
        }
    }
}
