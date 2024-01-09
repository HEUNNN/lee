package construct;

public class MemberInitMain3 {
    public static void main(String[] args) {
        MemberInit member1 = new MemberInit();
        MemberInit member2 = new MemberInit();
        member1.initMember("user1", 27, 100);
        member2.initMember("user2", 28, 90);

        MemberInit[] members = {new MemberInit(), member2};

        for (MemberInit member : members) {
            System.out.println("이름: " + member.name + ", 나이: " + member.age + ", 성적: " + member.grade);
        }
    }
}
