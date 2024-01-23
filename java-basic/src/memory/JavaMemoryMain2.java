package memory;

public class JavaMemoryMain2 {
    public static void main(String[] args) {
        System.out.println("main start");
        method1();
        // data1 인스턴스를 참조하는 곳이 없다. GC 대상이된다.
        System.out.println("main end");
    }
    static void method1() {
        System.out.println("method1 start");
        Data data1 = new Data(10);
        method2(data1); // method2에서는 method1에서 넘겨준 data1 인스턴스를 참조하게 된다.
        System.out.println("method1 end");
    }
    static void method2(Data data2) {
        System.out.println("method2 start");
        System.out.println("data.value = " + data2.getValue());
        System.out.println("method2 end");
    }
}
