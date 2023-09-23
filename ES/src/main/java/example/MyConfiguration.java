package example;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public MyService myService(ApplicationArguments applicationArguments) {
        return new MyService(applicationArguments);
    }
}
