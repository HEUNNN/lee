package BOJ.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Laboratory {
    // https://www.acmicpc.net/problem/14502
    static int N;
    static int M;
    static int[][] area;
    static int[][] copyArea;
    static int[][] spreadVirusArea;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        area = new int[N][M];
        copyArea = new int[N][M];
        spreadVirusArea = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
                copyArea[i][j] = area[i][j];
            }
        }
        makeWall(0);
        System.out.println(result);
    }

    private static void makeWall(int depth) {
        if (depth == 3) {
            spreadVirus();
            countSafeZone();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyArea[i][j] == 0) {
                    copyArea[i][j] = 1;
                    makeWall(depth + 1);
                    copyArea[i][j] = 0;
                }
            }
        }
    }

    private static void spreadVirus() {
        Queue<Position> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) { // deep copy
            for (int j = 0; j < M; j++) {
                spreadVirusArea[i][j] = copyArea[i][j];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (spreadVirusArea[i][j] == 2) {
                    queue.add(new Position(i, j));
                }
            }
        }

        while (!queue.isEmpty()) {
            Position curr = queue.poll();
            int x = curr.x;
            int y = curr.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (spreadVirusArea[nx][ny] == 0) {
                        spreadVirusArea[nx][ny] = 2;
                        queue.add(new Position(nx, ny));
                    }
                }
            }
        }
    }

    private static void countSafeZone() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (spreadVirusArea[i][j] == 0) {
                    count++;
                }
            }
        }
        result = Math.max(result, count);
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
