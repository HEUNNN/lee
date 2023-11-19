package lee.project.Connect.service;


import lee.project.Connect.util.ByteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;

    public void setValue(int keySpace, String key, String value) {
        RedisConnection command = lettuceConnectionFactory.getConnection();
        try {
            command.select(keySpace);
            command.set(key.getBytes(), value.getBytes());
        } catch (Exception e) {
            System.out.println("Exception occurred while set value, msg = " + e.getMessage());
        } finally {
            if (command != null) {
                command.close();
            }
        }
    }

    public String getValue(int keySpace, String key) {
        RedisConnection command = lettuceConnectionFactory.getConnection();
        String value = "";
        try {
            command.select(keySpace);
            value = ByteUtils.convertByteToString(command.get(key.getBytes()));
        } catch (Exception e) {
            System.out.println("Exception occurred while get value, msg = " + e.getMessage());
        } finally {
            if (command != null) {
                command.close();
            }
        }
        return value;
    }

    public void deleteValue(int keySpace, String key) {
        RedisConnection command = lettuceConnectionFactory.getConnection();
        try {
            command.select(keySpace);
            command.del(key.getBytes());
        } catch (Exception e) {
            System.out.println("Exception occurred while delete value, msg = " + e.getMessage());
        } finally {
            if (command != null) {
                command.close();
            }
        }
    }
    
    public void hset(int keySapce, String key, String field, String value) {
        RedisConnection command = lettuceConnectionFactory.getConnection();
        try {
            command.select(keySapce);
            command.hSet(key.getBytes(), field.getBytes(), value.getBytes());
        } catch (Exception e) {
            System.out.println("Exception occured while hset, msg = " + e.getMessage());
        } finally {
            if (command != null) {
                command.close();
            }
        }
    }
    
    public String hget(int keySpace, String key, String field) {
        RedisConnection command = lettuceConnectionFactory.getConnection();
        String value = "";
        try {
           command.select(keySpace);
            value = ByteUtils.convertByteToString(command.hGet(key.getBytes(), field.getBytes()));
        } catch (Exception e) {
            System.out.println("Excpetion occured while hget, msg = " + e.getMessage());
        } finally {
            if (command != null) {
                command.close();
            }
        }

        return value;
    }
}
