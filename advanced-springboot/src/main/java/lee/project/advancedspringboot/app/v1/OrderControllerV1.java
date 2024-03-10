package lee.project.advancedspringboot.app.v1;

import lee.project.advancedspringboot.trace.TraceStatus;
import lee.project.advancedspringboot.trace.helloTrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(@RequestParam("itemId") String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 로그 처리 이후 throw e를 해주어야함
            // 예외를 던져주지 않으면, catch 문에서 예외를 먹어버리고 이후에 정상 흐름인것처럼 동작하게 된다. '로그는 애플리케이션 흐름에 영향을 주면 안된다'는 요구사항을 설정하였다. 즉, 로그 처리로 인하여 예외가 사라지면 안된다.
        }
    }
}
