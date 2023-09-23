package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CoinV2 {
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        // https://www.acmicpc.net/problem/2294
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        coins = new int[n];
        dp = new int[k + 1];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        fillDp();
        System.out.println(dp[k] == 10001 ? -1 : dp[k]);

    }

    private static void fillDp() {
        initDp();
        for (int coin : coins) {
            for (int j = coin; j < dp.length; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }
    }

    private static void initDp() {
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 10001;
        }
    }
}
