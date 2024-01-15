package access.a;

public class PublicClass {
    // 하나의 .java 파일에 public 클래스는 하나만 등장할 수 있다.
    // 하나의 .java 파일에 default 접근 제어자를 사용하는 클래스는 무한정 만들 수 있다.

    public static void main(String[] args) {
        PublicClass publicClass = new PublicClass();
        DefaultClass1 defaultClass1 = new DefaultClass1();
        DefaultClass2 defaultClass2 = new DefaultClass2();
    }
}

class DefaultClass1 {

}

class DefaultClass2 {

}
