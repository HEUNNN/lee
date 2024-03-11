package programmers.lv2;

public class LongJump {
    public static void main(String[] args) {
        int n = 1;
        long answer = fibonacciDp(n);
        System.out.println(answer);

    }

    public static long fibonacciDp(int n) {
        if (n == 1) return 1;
        long[] fibonacci = new long[n + 1];
        fibonacci[1] = 1;
        fibonacci[2] = 2;
        for (int i = 3; i <= n; i++) {
            fibonacci[i] = (fibonacci[i - 1] % 1234567 + fibonacci[i - 2] % 1234567) % 1234567;
        }
        return fibonacci[n];
    }
}
