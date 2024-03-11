package lee.project.advancedspringboot;

import lee.project.advancedspringboot.trace.logtrace.FieldLogTrace;
import lee.project.advancedspringboot.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }
}
