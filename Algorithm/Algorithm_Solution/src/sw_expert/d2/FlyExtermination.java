package sw_expert.d2;

import java.util.Scanner;

class FlyExtermination {

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] map = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            System.out.println("#" + test_case + " " + result(m, map));
        }
    }

    public static int result(int m, int[][] map) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                max = Math.max(max, count(i, j, m, map));
            }
        }
        return max;
    }

    public static int count(int x, int y, int m, int[][] map) {
        int cnt = 0;
        for (int i = x; i < x + m; i++) {
            for (int j = y; j < y + m; j++) {
                if (i < map.length && j < map.length) {
                    cnt += map[i][j];
                }
            }
        }
        return cnt;
    }
}