package pack;
//import pack.a.User1;
//import pack.a.User2;

import pack.a.User1;
import pack.a.User2;


public class PackageMain3 {
    public static void main(String[] args) {
        Data data = new Data();
        User1 user1 = new User1(); // User 클래스와 메인 클래스의 패키지는 다르다. → import 방식 사용
        User2 user2 = new User2();

        pack.b.User1 user11 = new pack.b.User1(); // 이미지 pack.a의 User1 을 import 했기 때문에 pack.b의 User1은 import가 불가하다.
    }
}
