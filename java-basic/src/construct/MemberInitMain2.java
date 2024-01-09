package construct;

public class MemberInitMain2 {
    public static void main(String[] args) {
        MemberInit member1 = new MemberInit();
        MemberInit member2 = new MemberInit();
        initMember(member1, "user1", 27, 100);
        initMember(member2, "user2", 28, 90);

        MemberInit[] members = {new MemberInit(), member2};

        for (MemberInit member : members) {
            System.out.println("이름: " + member.name + ", 나이: " + member.age + ", 성적: " + member.grade);
        }
    }
    public static void initMember(MemberInit member, String name, int age, int grade) {
        member.name = name;
        member.age = age;
        member.grade = grade;
    }
}
