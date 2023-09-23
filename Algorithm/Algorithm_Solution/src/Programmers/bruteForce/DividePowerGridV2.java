package Programmers.bruteForce;

import java.util.HashSet;
import java.util.Set;

public class DividePowerGridV2 {
    public static void main(String[] args) {
        int n1 = 9;
        int[][] wires1 = new int[][]{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        int n2 = 4;
        int[][] wires2 = new int[][]{{1, 2}, {2, 3}, {3, 4}};
        int solution = new DividePowerGridV2().solution(n1, wires1);
        System.out.println(solution);
    }

    static int min;

    public int solution(int n, int[][] wires) {
        Set<Integer>[] adjArray = getAdjArray(n);
        boolean[] visited = new boolean[n + 1];
        int len = wires.length;
        min = n;
        for (int i = 0; i < len; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            adjArray[v1].add(v2);
            adjArray[v2].add(v1);
        }
        dfs(1, adjArray, visited);
        return min;
    }

    private int dfs(int start, Set<Integer>[] sets, boolean[] visited) {
        visited[start] = true;
        int count = 1;
        for (Integer linked : sets[start]) {
            if (!visited[linked]) {
                count += dfs(linked, sets, visited);
            }
        }
        System.out.println("dfs(" + start + ") end, " + count);
        min = Math.min(min, Math.abs(count - ((sets.length - 1) - count)));
        return count;
    }

    private Set<Integer>[] getAdjArray(int n) {
        Set<Integer>[] adjArray = new Set[n + 1];
        for (int i = 0; i <= n; i++) {
            adjArray[i] = new HashSet<>();
        }
        return adjArray;
    }
}
