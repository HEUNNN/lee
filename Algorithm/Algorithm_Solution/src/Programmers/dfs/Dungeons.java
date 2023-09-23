package Programmers.dfs;

public class Dungeons {
    // https://school.programmers.co.kr/learn/courses/30/lessons/87946

    /*
     * k(tired) = 80
     * dungeons = [[80, 20], [50, 40], [30, 10]]
     * dungeon[i][0] 보다 k가 커야 탐색 가능
     */
    static int answer = 0;
    static boolean[] visited;

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = new int[][]{{80, 20}, {50, 40}, {30, 10}};
        int solution = new Dungeons().solution(k, dungeons);
        System.out.println(solution);
    }

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        DFS(k, dungeons, 0);
        return answer;
    }

    private void DFS(int tired, int[][] dungeons, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print("  ");
        }
        System.out.print("DFS(" + tired + ", dungeons, " + count + ")");
        System.out.println("  visited = [" + visited[0] + ", " + visited[1] + ", " + visited[2] + "]");
        for (int i = 0; i < dungeons.length; i++) {
            if (dungeons[i][0] <= tired && !visited[i]) {
                visited[i] = true;
                DFS(tired - dungeons[i][1], dungeons, count + 1); // count ++ 하면 안됨
                visited[i] = false;
            }
        }
        answer = Math.max(count, answer);
    }
}
