package Programmers.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FarthestNode {
    // https://school.programmers.co.kr/learn/courses/30/lessons/49189
    static boolean[] visited;
    static int[] distances;
    static ArrayList<Integer>[] graph;
    static int maxDistance = Integer.MIN_VALUE;

    public static void main(String[] args) {
        int n = 6;
        int[][] vertex1 = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        int[][] vertex2 = {{1, 2}, {2, 3}, {3, 1}};
        int solution = new FarthestNode().solution(n, vertex1);
        System.out.println(solution);
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;
        visited = new boolean[n + 1];
        distances = new int[n + 1];
        graph = makeGrape(n, edge);
        bfs();
        for (int i = 1; i < n + 1; i++) {
            if (distances[i] == maxDistance) {
                answer++;
            }
        }
        return answer;
    }

    private void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while (!q.isEmpty()) {
            Integer currNode = q.poll();
            for (Integer nextNode : graph[currNode]) {
                if (distances[nextNode] == 0 && nextNode != 1) {
                    distances[nextNode] = distances[currNode] + 1;
                    maxDistance = Math.max(maxDistance, distances[nextNode]);
                    q.add(nextNode);
                }
            }
        }
    }

    private ArrayList<Integer>[] makeGrape(int n, int[][] edge) {
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            int n1 = edge[i][0];
            int n2 = edge[i][1];
            graph[n1].add(n2);
            graph[n2].add(n1);
        }

        return graph;
    }

}
