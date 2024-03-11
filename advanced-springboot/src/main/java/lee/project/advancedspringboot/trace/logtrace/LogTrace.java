package lee.project.advancedspringboot.trace.logtrace;

import lee.project.advancedspringboot.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message); // message를 넘겨주면 TraceId와 로그를 찍고 TraceStatus를 최종적으로 생성하여 반환하는 메서드
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
