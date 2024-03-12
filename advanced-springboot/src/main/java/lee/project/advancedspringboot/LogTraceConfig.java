package lee.project.advancedspringboot;

import lee.project.advancedspringboot.trace.logtrace.LogTrace;
import lee.project.advancedspringboot.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
