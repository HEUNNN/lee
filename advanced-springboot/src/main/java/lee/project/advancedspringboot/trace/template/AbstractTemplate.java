package lee.project.advancedspringboot.trace.template;

import lee.project.advancedspringboot.trace.TraceStatus;
import lee.project.advancedspringboot.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace; // 생성자 주입
    }

    public T excute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);// LogTrace 인터페이스의 begin 메서드는 TraceId 객체를 생성하고 TraceStatus 객체도 생성한다.

            // 로직 호출
            T result = call();
            trace.end(status);

            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
