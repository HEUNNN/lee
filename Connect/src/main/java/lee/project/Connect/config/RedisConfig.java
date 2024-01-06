package lee.project.Connect.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

    @Value("${redis.host:localhost}")
    public String host;

    @Value("${redis.port}")
    public int port;

    @Value("${redis.username}")
    public String username;

    @Value("${redis.password}")
    public String password;

    @Bean
    LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(host, port);
        redisConfig.setUsername(username);
        redisConfig.setPassword(password);
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisConfig);
        lettuceConnectionFactory.setShareNativeConnection(false); // command.select 시 예외가 발생하여 false 로 설정, default 는 ture
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }
}
