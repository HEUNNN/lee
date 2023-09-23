package BOJ.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ApartmentBlockNumber {
    // https://www.acmicpc.net/problem/2667
    static int[] dy = {-1, +1, 0, 0}; // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, +1};
    static int len;
    static int[][] houseMap;
    static boolean[][] visited;
    static int[] blockCount = new int[25 * 25]; // 생길 수 있는 단지 번호에 속하는 아파트가 몇개인지 기입할 배열
    static int blockNum = 0; // 현재 단지 번호


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        len = Integer.parseInt(br.readLine());

        houseMap = new int[len][len];
        visited = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            String numbers = br.readLine();
            for (int j = 0; j < numbers.length(); j++) {
                int i1 = numbers.charAt(j) == '1' ? (houseMap[i][j] = 1) : (houseMap[i][j] = 0);
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (houseMap[i][j] == 1 && !visited[i][j]) {
                    blockNum++;
//                    dfs(i, j);
                    bfs(i, j);
                }
            }
        }

        Arrays.sort(blockCount);

        System.out.println(blockNum);
        for (int i : blockCount) {
            if (i > 0) System.out.println(i);
        }
    }

    private static void dfs(int y, int x) {
        visited[y][x] = true;
        blockCount[blockNum]++;
        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k]; // next x
            int nx = x + dx[k]; // next y
            if (ny >= 0 && nx >= 0 && ny < len && nx < len) {
                if (houseMap[ny][nx] == 1 && !visited[ny][nx]) {
                    dfs(ny, nx);
                }
            }
        }
    }

    private static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        visited[y][x] = true;
        blockCount[blockNum]++;

        while (!queue.isEmpty()) {
            int curY = queue.peek()[0];
            int curX = queue.peek()[1];
            queue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];
                if (ny >= 0 && nx >= 0 && ny < len && nx < len) {
                    if (houseMap[ny][nx] == 1 && !visited[ny][nx]) {
                        queue.add(new int[]{ny, nx});
                        visited[ny][nx] = true;
                        blockCount[blockNum]++;
                    }
                }
            }
        }
    }

}
