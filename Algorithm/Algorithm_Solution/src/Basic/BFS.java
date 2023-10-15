package Basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        // Graph를 2차원 배열로 표현
        // n번 인덱스에 연결된 노드의 값을 의미 → 1번 노드에 2, 3, 8번 노드가 연결 되어 있다.
        int[][] graph = {{}, {2, 3, 8}, {1, 6, 8}, {1, 5}, {5, 7}, {3, 4, 7}, {2}, {4, 5}, {1, 2}};
        boolean[] visited = new boolean[9];

        List<Integer> bfs = new BFS().bfs(1, graph, visited);
        for (Integer bf : bfs) {
            System.out.println(bf);
        }
    }

    private List<Integer> bfs(int start, int[][] graph, boolean[] visited) {
        List<Integer> result = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result.add(curr);

            for (int i = 0; i < graph[curr].length; i++) {
                int linked = graph[curr][i];
                if (!visited[linked]) {
                    queue.add(linked);
                    visited[linked] = true;
                }
            }

        }
        return result;
    }
}
