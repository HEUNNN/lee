package BOJ.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SheepRescue {

    static ArrayList<Integer>[] adjList;
    static long[] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];
        initAdjList();
        dp = new long[N + 1];
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String animal = st.nextToken();
            int amount = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            adjList[prev].add(i);
            dp[i] = amount;
            if (animal.equals("W")) dp[i] = -dp[i];
        }
        dfs(1, -1);
        System.out.println(dp[1]);
    }

    private static void dfs(int curr, int upper) {
        for (Integer next : adjList[curr]) {
            dfs(next, curr);
        }
        if (upper != -1) {
            if (dp[curr] > 0) {
                dp[upper] += dp[curr];
            }
        }
    }

    private static void initAdjList() {
        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
    }
}