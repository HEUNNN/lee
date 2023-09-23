package Programmers.dfs;

import java.util.ArrayList;

public class Network {
    // https://school.programmers.co.kr/learn/courses/30/lessons/43162
    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] computers2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int solution = new Network().solution(n, computers2);
        System.out.println(solution);

    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        ArrayList<Integer>[] adjList = initAdjList(n);
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (computers[i][j] == 1) {
                    adjList[i].add(j);
                    adjList[j].add(i);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, adjList, visited);
                answer++;
            }
        }

        return answer;
    }

    private void dfs(int x, ArrayList<Integer>[] adjList, boolean[] visited) {
        if (adjList[x].isEmpty() || visited[x]) return;
        visited[x] = true;
        for (Integer linked : adjList[x]) {
            dfs(linked, adjList, visited);
        }
    }

    private ArrayList<Integer>[] initAdjList(int n) {
        ArrayList<Integer>[] listArray = new ArrayList[n];
        for (int i = 0; i < listArray.length; i++) {
            listArray[i] = new ArrayList<>();
        }
        return listArray;
    }
}
