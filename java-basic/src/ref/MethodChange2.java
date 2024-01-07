package ref;

public class MethodChange2 {
    public static void main(String[] args) {
        Data dataA = new Data(10);
        System.out.println("메서드 호출 전 dataA.value = " + dataA.value);
        changeReference(dataA);
        System.out.println("메서드 호출 후 dataA.value = " + dataA.value);

    }

    static void changeReference(Data data) {
        // data 변수는 메서드 호출 시 넘겨받은 Data 객체(인스턴스)의 참조값(주소값)을 복사해서 전달받는다.
        data.value *= 2;
    }
}
