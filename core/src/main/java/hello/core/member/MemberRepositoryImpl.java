package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemberRepositoryImpl implements MemberRepository {
    private static Map<Long, Member> storage = new HashMap<>();

    @Override
    public void save(Member member) {
        storage.put(member.getId(), member);
    }

    @Override
    public Member findById(Long id) {
        return storage.get(id);
    }
}
