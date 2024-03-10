package lee.project.advancedspringboot.trace;

public class TraceStatus {
    // Trace Status → 로그 기록 시작 시 상태 정보를 의미함, 해당 상태 정보는 로그 종료 시 사용됨
    private TraceId traceId; // 내부 트랜잭션 ID와 level 정보
    private Long startTimeMs; // 로그 시작 시간 → 해당 로그 시작 시간을 기준으로 시작 - 종료까지의 수행 시간을 기록할 수 있음
    private String message; // 로그 시작 / 종료 시 사용할 메시지

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
