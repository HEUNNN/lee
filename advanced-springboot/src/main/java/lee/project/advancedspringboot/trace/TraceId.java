package lee.project.advancedspringboot.trace;

import java.util.UUID;

public class TraceId {
    // 로그 추적기는 트랜잭션 ID와 깊이를 표현하는 방법이 필요
    // → 트랜잭션 ID와 깊이를 표현하는 level을 묶어서 'TraceId' 라는 클래스를 생성

    private String id; // Transaction ID
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPrevId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
