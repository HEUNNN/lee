package BOJ.implementation;

public class SelfNumber {
    // https://www.acmicpc.net/problem/4673
    static int[] numbers;

    public static void main(String[] args) {
        numbers = new int[10001];
        for (int n = 1; n < numbers.length; n++) {
            selfNumber(n);
        }
        for (int n = 1; n < numbers.length; n++) {
            if (numbers[n] == 0) {
                System.out.println(n);
            }
        }
    }

    public static void selfNumber(int n) {
        int sum = n;
        while (n > 0 && sum < numbers.length) {
            sum += n % 10;
            n /= 10;
        }
        if (sum < numbers.length) {
            numbers[sum] = 1;
        }
    }
}
