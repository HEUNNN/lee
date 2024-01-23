package static1;

public class Data3 {
    public String name; // 인스턴스 멤버 변수
    public static int count; // 클래스 멤버 변수(static 멤버 변수)

    public Data3(String name) {
        this.name = name;
        count++;
    }
}
