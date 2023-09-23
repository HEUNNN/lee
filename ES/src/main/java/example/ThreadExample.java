package example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor(); // main 스레드와 별개의 스레드로 함수 실행 시키는 예시
        executorService.execute(() -> esTask());
        mainTask();
    }

    private static void esTask() {
        for (int i = 0; i < 10; i++) {
            log.info("es task...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void mainTask() {
        for (int i = 0; i < 1000; i++) {
            log.info("[{}] main task...", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
