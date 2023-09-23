package Programmers.dfs;

public class Tired {
    // https://school.programmers.co.kr/learn/courses/30/lessons/87946
    static int max = 0;
    static boolean[] visited;

    public static void main(String[] args) {
        int[][] dungeons = new int[][]{{80, 20}, {50, 40}, {30, 10}};
        int solution = new Tired().solution(80, dungeons);
        System.out.println(solution);
    }

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return max;
    }

    public void dfs(int k, int[][] dungeons, int depth) {
        for (int i = 0; i < dungeons.length; i++) {
            int minPirodo = dungeons[i][0];
            int usePirodo = dungeons[i][1];
            if (k >= minPirodo && !visited[i]) {
                visited[i] = true;
                dfs(k - usePirodo, dungeons, depth + 1);
                visited[i] = false;
            }
        }
        max = Math.max(max, depth);
    }
}
