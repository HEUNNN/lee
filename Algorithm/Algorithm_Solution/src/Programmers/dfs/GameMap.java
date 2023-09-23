package Programmers.dfs;

import java.util.LinkedList;
import java.util.Queue;

public class GameMap {
    // https://school.programmers.co.kr/learn/courses/30/lessons/1844
    // 동서남북
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int n;
    static int m;

    public static void main(String[] args) {
        int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        int[][] maps2 = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}};
        int solution = new GameMap().solution(maps2);
        System.out.println(solution);
    }

    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        int[][] visited = new int[n][m];
        bfs(maps, visited);
        int result = visited[n - 1][m - 1];
        return result == 0 ? -1 : result;
    }

    private void bfs(int[][] maps, int[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (maps[nx][ny] == 1 && visited[nx][ny] == 0) {
                    visited[nx][ny] = visited[curr[0]][curr[1]] + 1;
                    queue.add(new int[]{nx, ny});
                }

            }
        }
    }

}
