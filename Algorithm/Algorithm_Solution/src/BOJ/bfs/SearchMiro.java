package BOJ.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SearchMiro {
    // https://www.acmicpc.net/problem/2178
    static int n;
    static int m;
    static int[][] miro;
    static boolean[][] visited;
    static int[] dRow = {-1, 1, 0, 0}; // 상하좌우
    static int[] dCol = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        miro = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String inputString = br.readLine();
            for (int j = 0; j < inputString.length(); j++) {
                miro[i][j] = inputString.charAt(j) == '1' ? 1 : 0;
            }
        }
        bfs(0, 0);
        System.out.println(miro[n - 1][m - 1]);

    }

    private static void bfs(int startRow, int startCol) {
        Queue<int[]> queue = new LinkedList<>();
        visited[startRow][startCol] = true;
        queue.add(new int[]{startRow, startCol});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nRow = now[0] + dRow[i];
                int nCol = now[1] + dCol[i];
                if (nRow >= 0 && nCol >= 0 && nRow < n && nCol < m) { // 좌표 유효성 검사
                    if (!visited[nRow][nCol] && miro[nRow][nCol] == 1) {
                        queue.add(new int[]{nRow, nCol});
                        visited[nRow][nCol] = true;
                        miro[nRow][nCol] = miro[now[0]][now[1]] + 1; // count
                    }

                }
            }
        }
    }
}
