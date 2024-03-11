package lee.project.advancedspringboot.app.v3;

import lee.project.advancedspringboot.trace.TraceStatus;
import lee.project.advancedspringboot.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(@RequestParam("itemId") String itemId) {
        // 단일 HTTP 요청에 대한 트랜잭션 ID가 유지되고, 레벨도 표기되도록 적용
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId); // traceId 객체를 파라미터로 넘겨주어야 함(단점) → traceId 객체는 트랜잭션 ID 값과 레벨을 갖고 있음
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
