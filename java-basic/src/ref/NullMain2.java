package ref;

public class NullMain2 {
    public static void main(String[] args) {
        BigData bigData = new BigData();
        bigData.data = new Data();

        System.out.println("bigData.count = " + bigData.count); // 0 출력
        System.out.println("bigData.data = " + bigData.data); // null 출력

        System.out.println("bigData.data.value = " + bigData.data.value); // 0 출력

    }
}
