package lee.project.contentinjector.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class RedisConfig {

    @Value("${redis.host:localhost}")
    public String host;

    @Value("${redis.port}")
    public int port;

    @Value("${redis.username:}")
    public String username;

    @Value("${redis.password}")
    public String password;

    LettuceConnectionFactory lettuceConnectionFactory;

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(host, port);
        redisConfig.setUsername(username);
        redisConfig.setPassword(password);
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisConfig);
        lettuceConnectionFactory.setShareNativeConnection(false);
        lettuceConnectionFactory.afterPropertiesSet();

        this.lettuceConnectionFactory = lettuceConnectionFactory;

        return lettuceConnectionFactory;
    }

    @Bean
    @DependsOn("lettuceConnectionFactory")
    RedisConnection redisConnection() {
        return lettuceConnectionFactory.getConnection();
    }

}

