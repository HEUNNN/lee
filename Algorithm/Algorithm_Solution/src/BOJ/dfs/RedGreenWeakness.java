package BOJ.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RedGreenWeakness {
    // https://www.acmicpc.net/problem/10026
    static int n;
    static char[][] colors;
    static char[][] weaknessColors; // 적록 색약의 색들
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // 적록색약은 R == G 로 인식한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        colors = new char[n][n];

        for (int i = 0; i < n; i++) {
            String colorString = br.readLine();
            for (int j = 0; j < n; j++) {
                colors[i][j] = colorString.charAt(j);
            }
        }

        int normalCount = getAreaCount(colors);

        weaknessColors = redGreenWeakness(n, colors); // 적록색약 전용 color 배열 생성
        int weaknessCount = getAreaCount(weaknessColors);

        System.out.println(normalCount + " " + weaknessCount);
    }

    public static int getAreaCount(char[][] array) {
        visited = new boolean[n][n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) continue;
                dfs(i, j, array[i][j], array);
                cnt++;
            }
        }
        return cnt;
    }

    public static void dfs(int x, int y, char target, char[][] charArr) {
        visited[x][y] = true;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if (!visited[nx][ny] && charArr[nx][ny] == target) {
                    dfs(nx, ny, target, charArr);
                }
            }
        }
    }

    private static char[][] redGreenWeakness(int size, char[][] original) {
        char[][] weaknessColors = new char[size][size];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (original[i][j] == 'R' || original[i][j] == 'G') weaknessColors[i][j] = 'R';
                else weaknessColors[i][j] = 'B';
            }
        }
        return weaknessColors;
    }
}