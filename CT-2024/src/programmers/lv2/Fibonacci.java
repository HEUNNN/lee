package programmers.lv2;

public class Fibonacci {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12945
    public static void main(String[] args) {
        int f1 = fibonacciRecursive(4);
        int f2 = fibonacciDp(4);
        System.out.println(f1);
        System.out.println(f2);
    }

    public static int fibonacciRecursive(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else {
            return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
        }
    }

    public static int fibonacciDp(int n) {
        int[] fibonacci = new int[n + 1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        for (int i = 2; i <= n; i++) {
            fibonacci[i] = (fibonacci[i - 1] % 1234567 + fibonacci[i - 2] % 1234567) % 1234567;
        }
        return fibonacci[n];
    }
}
