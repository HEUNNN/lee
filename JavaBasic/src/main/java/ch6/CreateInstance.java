package ch6;

public class CreateInstance {
    public static void main(String[] args) {
        Tv tv = new Tv();
        // Tv 인스턴스(객체) 생성 시 String, boolean, int 초기값이 어떻게 되는지 출력
        System.out.println(tv.color);
        System.out.println(tv.channel);
        System.out.println(tv.power);
    }
}
