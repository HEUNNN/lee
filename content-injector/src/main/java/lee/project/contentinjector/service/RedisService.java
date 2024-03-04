package lee.project.contentinjector.service;

import lee.project.contentinjector.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    RedisConnection client;


    public void connectTest() {
        try {
            client.select(1);
            client.get("test1".getBytes());
        } catch (Exception e) {
            System.out.println("exception, e msg = " + e.getMessage());
        }
    }

    public void setContentInjectStatus(String targetSeq, String status) {
        try {
            String field = targetSeq + RedisConstant.CONTENT_INJECT_STATUS_FIELD_SUFFIX;
            client.select(RedisConstant.CONTENT_INJECT_STATUS_KEYSPACE);
            client.hSet(RedisConstant.CONTENT_INJECT_STATUS_HKEY.getBytes(), field.getBytes(), status.getBytes());
        } catch (Exception e) {
            // TODO
        }
    }

    public boolean isExistsContentInjectStatus(String targetSeq) {
        try {
            String field = targetSeq + RedisConstant.CONTENT_INJECT_STATUS_FIELD_SUFFIX;
            client.select(RedisConstant.CONTENT_INJECT_STATUS_KEYSPACE);
            return Boolean.TRUE.equals(client.hExists(RedisConstant.CONTENT_INJECT_STATUS_HKEY.getBytes(), field.getBytes()));
        } catch (Exception e) {
            // TODO
            throw e;
        }
    }

}
