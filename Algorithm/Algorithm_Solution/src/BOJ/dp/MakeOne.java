package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MakeOne {
    // https://www.acmicpc.net/problem/1463
    static int[] dp; // Interger[] 배열은 처음에 초기화할 때 원소의 값을 따로 정해주지 않으면 null이 되고, int[]는 지정해주지 않으면 0이 된다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(0);
            return;
        }
        dp = new int[n + 1];
        bottomUp(n);
        System.out.println(dp[n]);
    }

    private static void bottomUp(int n) {
        for (int i = 2; i <= n; i++) {

            // 1을 빼는 경우
            dp[i] = dp[i - 1] + 1; // 앞의 number보다 +1 크니까 '+1'만 해주면 1을 빼서 구할 때 최소의 횟수를 구할 수 있다.

            // 2로 나누어 떨어지는 경우
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1); // 1을 빼서 1로 만든 경우 → dp[i]
            }

            // 3으로 나누어 떨어지는 경우
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1); // 1을 빼서 1로 만든 경우 → dp[i]
            }
        }
    }
}
