package BOJ.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SafetyArea {
    // https://www.acmicpc.net/problem/2468
    static int size;
    static int[][] area;
    static int countMax;
    static int max;
    static int min;
    static int[] dy = {-1, +1, 0, 0};
    static int[] dx = {0, 0, -1, +1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        area = new int[size][size];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        countMax = 1; // Integer.MIN_VALUE로 하면 안됨


        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                int target = Integer.parseInt(st.nextToken());
                max = Math.max(max, target);
                min = Math.min(min, target);
                area[i][j] = target;
            }
        }

        for (int i = min; i <= max; i++) {
            int[][] testArea = suddenArea(i); // 특정 수위일 때 잠겨있는 영역을 표시한 임시 Area 2차 배열
            int countTmp = countSafeArea(testArea);
            countMax = Math.max(countMax, countTmp);
        }
        System.out.println(countMax);
    }

    private static int countSafeArea(int[][] testArea) { // 특정 수위에 의해 잠기거나 안잠긴 area가 주어졌을 때 안전한 영역을 count 한다.
        boolean[][] visited = new boolean[size][size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (testArea[i][j] == -1) continue;
                if (!visited[i][j]) {
                    dfs(testArea, visited, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(int[][] testArea, boolean[][] visited, int y, int x) {
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny >= 0 && nx >= 0 && ny < size && nx < size) {
                if (testArea[ny][nx] == 1 && !visited[ny][nx]) {
                    dfs(testArea, visited, ny, nx);
                }
            }
        }
    }


    private static int[][] suddenArea(int height) { // 특정 수위에 의해 잠긴 area는 -1, 안잠긴 area는 1로 표시
        int[][] copyArea = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (area[i][j] <= height) {
                    copyArea[i][j] = -1;
                } else {
                    copyArea[i][j] = 1;
                }
            }
        }
        return copyArea;
    }
}
