package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // MemberServiceImpl은 MemberRepository 인터페이스(추상화)에도 의존하고, MemberRepositoryImpl(구체화)에도 의존한다.
    // DIP 위반

    private final MemberRepository repository = new MemberRepositoryImpl();

    @Override
    public void join(Member member) {
        repository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return repository.findById(id);
    }
}
