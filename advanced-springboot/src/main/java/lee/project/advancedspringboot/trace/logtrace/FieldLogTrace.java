package lee.project.advancedspringboot.trace.logtrace;

import lee.project.advancedspringboot.trace.TraceId;
import lee.project.advancedspringboot.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

import static lee.project.advancedspringboot.trace.LogConstant.*;

@Slf4j
public class FieldLogTrace implements LogTrace{
    // @Component scan을 사용하지 않고 직접 빈으로 등록할 예정
    private TraceId traceIdHolder; // TraceId 동기화용 필드 → 동시성 이슈가 발생할 수 있음
    @Override
    public TraceStatus begin(String message) {
        // TraceId 객체를 생성하고 이를 사용하여 로그를 찍고 TraceStatus 도 생성한다.
        // HTTP 요청 한 개당 TraceStatus 하나가 배정된다고 보면 된다. HTTP 요청 안에서 로그 깊이는 TraceStatus의 TraceId의 level 필드로 결정된다.
        syncTraceId();
        TraceId traceId = traceIdHolder;
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) { // end
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        } else { // exception
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }
        releaseTraceId();
    }

    private void syncTraceId() {
        if (traceIdHolder == null) {
            traceIdHolder = new TraceId();
        } else {
            // 메서드를 추가로 호출할 때는 level 을 증가
            traceIdHolder = traceIdHolder.createNextId();
        }
    }

    private void releaseTraceId() {
        if (traceIdHolder.isFirstLevel()) {
            traceIdHolder = null; // destroy
        } else {
            // 메서드 호출이 하나 끝나면 level을 감소
            traceIdHolder = traceIdHolder.createPrevId();
        }
    }

    public String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }
}
