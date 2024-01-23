package static1;

public class DataCountMain3 {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Data3 data1 = new Data3("A");
        System.out.println("A count = " + Data3.count);

        Data3 data2 = new Data3("B");
        System.out.println("B count = " + Data3.count);

        Data3 data3 = new Data3("C");
        System.out.println("C count = " + Data3.count);

        // 인스턴스를 통한 클래스 변수(static 변수, 정적 변수)
        Data3 data4 = new Data3("D");
        System.out.println("D count = " + data4.count);
    }
}
