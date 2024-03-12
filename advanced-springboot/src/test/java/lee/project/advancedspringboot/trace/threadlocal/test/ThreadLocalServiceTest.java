package lee.project.advancedspringboot.trace.threadlocal.test;

import lee.project.advancedspringboot.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    public void threadLocalTest() {
        log.info("main start");
        Runnable userA = () -> {
            threadLocalService.logic("userA");
        } ;
        Runnable userB = () -> {
            threadLocalService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(500); // sleep 을 500ms로 하여도 동시성 문제가 발생하지 않음
        threadB.start();

        sleep(2000);
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
