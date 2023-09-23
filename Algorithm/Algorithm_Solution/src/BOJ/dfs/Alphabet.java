package BOJ.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Alphabet {
    // https://www.acmicpc.net/problem/1987
    static int R;
    static int C;
    static int[][] alphabets;
    static boolean[] visit = new boolean[26];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxCount = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphabets = new int[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                alphabets[i][j] = line.charAt(j) - 'A';
            }
        }
//        dfsV1(0, 0, 0);
        dfsV2(0, 0, 0);
        System.out.println(maxCount);

    }

    private static void dfsV1(int x, int y, int count) {
        // 지금 데이터가 지난 알파벳이면 끝내야함
        if (x < 0 || y < 0 || x >= R || y >= C) {
            maxCount = Math.max(maxCount, count);
            return;
        }
        int current = alphabets[x][y];
        if (visit[current]) {
            maxCount = Math.max(maxCount, count);
            return;
        }

        // 상
        visit[current] = true;
        dfsV1(x - 1, y, count + 1);
        visit[current] = false;

        // 하
        visit[current] = true;
        dfsV1(x + 1, y, count + 1);
        visit[current] = false;

        // 좌
        visit[current] = true;
        dfsV1(x, y - 1, count + 1);
        visit[current] = false;

        // 우
        visit[current] = true;
        dfsV1(x, y + 1, count + 1);
        visit[current] = false;
    }

    private static void dfsV2(int x, int y, int count) {
        int current = alphabets[x][y];
        if (visit[current]) {
            maxCount = Math.max(maxCount, count);
            return;
        }
        visit[current] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                dfsV2(nx, ny, count + 1);
            }
        }
        visit[current] = false;
    }
}