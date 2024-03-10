package lee.project.advancedspringboot.app.v2;

import lee.project.advancedspringboot.trace.TraceId;
import lee.project.advancedspringboot.trace.TraceStatus;
import lee.project.advancedspringboot.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderService.orderItem()"); // HelloTraceV1 에서 TraceStatus를 생성하여 반환
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
