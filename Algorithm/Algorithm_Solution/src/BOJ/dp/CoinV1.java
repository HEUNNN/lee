package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CoinV1 {
    // https://www.acmicpc.net/problem/2293
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        coins = new int[N];
        dp = new int[K + 1];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        dp[0] = 1;
        fillDp();
        System.out.println(dp[K]);
    }

    private static void fillDp() {
        for (int coin : coins) {
            for (int i = coin; i < dp.length; i++) {
                dp[i] = dp[i - coin] + dp[i];
            }
        }
    }

}
