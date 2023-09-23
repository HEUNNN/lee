package BOJ.dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class IslandCount {
    // https://www.acmicpc.net/problem/4963
    static int col;
    static int row;
    static int[][] island;
    static int[] dr = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int[] dc = {-1, -1, -1, 0, 1, 1, 1, 0};
    static boolean[][] visited;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        result = new ArrayList<>();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            col = Integer.parseInt(st.nextToken()); // col
            row = Integer.parseInt(st.nextToken()); // row
            island = new int[row][col];
            visited = new boolean[row][col];
            int count = 0;

            if (col == 0 && row == 0) break;

            for (int i = 0; i < row; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < col; j++) {
                    island[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (!visited[i][j] && island[i][j] == 1) {
                        dfs(i, j);
                        count++;
                    }
                }
            }
            bw.write(count + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static void dfs(int r, int c) {
        visited[r][c] = true;
        for (int k = 0; k < 8; k++) {
            int nextRow = r + dr[k];
            int nextCol = c + dc[k];
            if (nextRow >= 0 && nextCol >= 0 && nextRow < row && nextCol < col) {
                if (island[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                    dfs(nextRow, nextCol);
                }
            }
        }
    }
}