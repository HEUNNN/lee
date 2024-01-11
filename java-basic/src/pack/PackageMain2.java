package pack;
//import pack.a.User1;
//import pack.a.User2;

 import pack.a.*;


public class PackageMain2 {
    public static void main(String[] args) {
        Data data = new Data();
        User1 user1 = new User1(); // User 클래스와 메인 클래스의 패키지는 다르다. → import 방식 사용
        User2 user2 = new User2();
    }
}
