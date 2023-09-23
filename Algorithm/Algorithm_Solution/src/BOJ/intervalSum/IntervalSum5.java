package BOJ.intervalSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IntervalSum5 {
    // https://www.acmicpc.net/problem/11660
    static int n;
    static int m;
    static int[][] numbers;
    static int[][] intervalSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[n + 1][n + 1];
        intervalSum = new int[n][n];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                numbers[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getIntervalSum();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            System.out.println(getSumResult(x1, y1, x2, y2));
        }
    }

    private static void getIntervalSum() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                numbers[i][j] = numbers[i - 1][j] + numbers[i][j - 1] - numbers[i - 1][j - 1] + numbers[i][j];
            }
        }
    }

    private static int getSumResult(int x1, int y1, int x2, int y2) {
        return numbers[x2][y2] - numbers[x2][y1 - 1] - numbers[x1 - 1][y2] + numbers[x1 - 1][y1 - 1];
    }
}
