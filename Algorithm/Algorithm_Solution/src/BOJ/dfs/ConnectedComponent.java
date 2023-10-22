package BOJ.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ConnectedComponent {
    // https://www.acmicpc.net/problem/11724
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 노드 개수
        int M = Integer.parseInt(st.nextToken()); // 엣지 개수

        List<Integer>[] nodes = new ArrayList[N + 1];
        setListArray(nodes);

        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int linked = Integer.parseInt(st.nextToken());

            nodes[start].add(linked);
            nodes[linked].add(start);
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
//                dfs(nodes, i, visited);
                bfs(nodes, i, visited);
                answer++;
            }
        }

        System.out.println(answer);
    }

    public static void setListArray(List<Integer>[] listArray) {
        for (int i = 0; i < listArray.length; i++) {
            listArray[i] = new ArrayList<>();
        }
    }

    public static void dfs(List<Integer>[] nodes, Integer start, boolean[] visited) {
        if (visited[start]) {
            return;
        }
        visited[start] = true;

        for (Integer linkedNode : nodes[start]) {
            if (!visited[linkedNode]) {
                dfs(nodes, linkedNode, visited);
            }
        }
    }

    public static void bfs(List<Integer>[] nodes, Integer start, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            Integer currNode = queue.poll();
            for (Integer linkedNode : nodes[currNode]) {
                if (!visited[linkedNode]) {
                    queue.add(linkedNode);
                    visited[linkedNode] = true;
                }
            }
        }
    }
}
