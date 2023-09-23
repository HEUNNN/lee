package example;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;

import java.util.Arrays;

@RequiredArgsConstructor
public class MyService {

    private final ApplicationArguments applicationArguments;

//    @PostConstruct
    public void test() {
        System.out.println("----------------MyService----------------");
        System.out.println(Arrays.toString(applicationArguments.getSourceArgs()));
        System.out.println("----------------MyService----------------");
    }
}
