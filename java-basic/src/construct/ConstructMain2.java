package construct;

public class ConstructMain2 {
    public static void main(String[] args) {
        MemberConstruct member1 = new MemberConstruct("user1", 27, 100);
        MemberConstruct member2 = new MemberConstruct("user2", 28, 90);

        MemberConstruct member3 = new MemberConstruct("user3", 25);

        MemberConstruct[] members = {member1, member2, member3};

        for (MemberConstruct member : members) {
            System.out.println("이름: " + member.name + ", 나이: " + member.age + ", 성적: " + member.grade);
        }
    }
}
