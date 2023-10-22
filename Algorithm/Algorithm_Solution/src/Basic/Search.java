package Basic;

import java.util.*;

public class Search {
    public static void main(String[] args) {
        // Graph를 2차원 배열로 표현
        // n번 인덱스에 연결된 노드의 값을 의미 → 1번 노드에 2, 3, 8번 노드가 연결 되어 있다.
//        int[][] graph = {{}, {2, 3, 8}, {1, 6, 8}, {1, 5}, {5, 7}, {3, 4, 7}, {2}, {4, 5}, {1, 2}};
        int[][] graph = {{}, {2, 3}, {5, 6}, {4}, {6}, {2}, {2, 4}};
        boolean[] bfsVisited = new boolean[graph.length];
        boolean[] dfsVisited1 = new boolean[graph.length];
        boolean[] dfsVisited2 = new boolean[graph.length];

        Search search = new Search();

        System.out.println("===================BFS===================");
        List<Integer> bfsResult = search.bfsUsingQueue(1, graph, bfsVisited);
        for (Integer node : bfsResult) {
            System.out.println(node);
        }

        System.out.println("===================DFS Recursion===================");
        List<Integer> dfsResult1 = new ArrayList<>();
        search.dfsUsingRecursion(1, graph, dfsVisited1, dfsResult1);
        for (Integer node : dfsResult1) {
            System.out.println(node);
        }

        System.out.println("===================DFS Stack===================");
        List<Integer> dfsResult2 = search.dfsUsingStack(1, graph, dfsVisited2);
        for (Integer node : dfsResult2) {
            System.out.println(node);
        }
    }

    private List<Integer> bfsUsingQueue(int start, int[][] graph, boolean[] visited) {
        List<Integer> bfsResult = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Integer currNode = queue.poll();
            bfsResult.add(currNode);
            visited[currNode] = true;

            for (int linkedNode : graph[currNode]) {
                if (!visited[linkedNode]) {
                    queue.add(linkedNode);
                    visited[linkedNode] = true;
                }
            }
        }
        return bfsResult;
    }

    private void dfsUsingRecursion(int currNode, int[][] graph, boolean[] visited, List<Integer> answer) {
        if (!visited[currNode]) {
            answer.add(currNode);
            visited[currNode] = true;
        } else {
            return;
        }
        for (int linkedNode : graph[currNode]) {
            dfsUsingRecursion(linkedNode, graph, visited, answer);
        }
    }

    private List<Integer> dfsUsingStack(int start, int[][] graph, boolean[] visited) {
        List<Integer> dftResult = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.add(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            Integer currNode = stack.pop();
            dftResult.add(currNode);
            for (int nextNode : graph[currNode]) {
                if (!visited[nextNode]) {
                    stack.add(nextNode);
                    visited[nextNode] = true;
                }
            }
        }
        return dftResult;
    }
}
