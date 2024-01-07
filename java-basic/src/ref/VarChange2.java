package ref;

public class VarChange2 {
    public static void main(String[] args) {
        Data dataA = new Data(10);
        Data dataB = dataA; // dataA 변수에 들어있는 참조값만 복사하여 dataB 변수로 전달됨

        System.out.println("dataA 참조값: " + dataA);
        System.out.println("dataB 참조값: " + dataB);
        System.out.println("dataA.value: " + dataA.value);
        System.out.println("dataB.value: " + dataB.value);

        dataA.value = 20;
        System.out.println("dataA.value: " + dataA.value);
        System.out.println("dataB.value: " + dataB.value);

        dataB.value = 30;
        System.out.println("dataA.value: " + dataA.value);
        System.out.println("dataB.value: " + dataB.value);
    }
}
