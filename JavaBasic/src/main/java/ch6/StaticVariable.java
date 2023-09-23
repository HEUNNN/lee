package ch6;

public class StaticVariable {
    public static int count = 0;
    public int serialNo;
    {
        ++count;
        serialNo = count;
    }
}