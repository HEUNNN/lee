package ohter;

import ch6.StaticVariable;

public class TT {
    public static void main(String[] args) {
        StaticVariable t1 = new StaticVariable();
        StaticVariable t2 = new StaticVariable();
        StaticVariable t3 = new StaticVariable();
        System.out.println(StaticVariable.count);

        System.out.println(t1.serialNo);
        System.out.println(t2.serialNo);
        System.out.println(t3.serialNo);
    }
}
