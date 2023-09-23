package Programmers.bruteForce;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class DividePowerGridV1 {
    // https://school.programmers.co.kr/learn/courses/30/lessons/86971
    public static void main(String[] args) {
        int n = 9;
        int[][] wires = new int[][]{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        int solution = new DividePowerGridV1().solution(n, wires);
        System.out.println(solution);
    }

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        int wireNumber = wires.length;
        for (int i = 0; i < wireNumber; i++) {
            answer = Math.min(answer, findDiff(i, n, wires));
        }
        return answer;
    }

    private int findDiff(int cutWire, int n, int[][] wires) {
        Set<Integer>[] set = initSet(n);
        boolean[] visited = new boolean[n];
        for (int i = 0; i < wires.length; i++) {
            if (i != cutWire) {
                set[wires[i][0] - 1].add(wires[i][1] - 1);
                set[wires[i][1] - 1].add(wires[i][0] - 1);
            }
        }
        int part1 = bfs(0, set, visited);
        int part2 = n - part1;
        return Math.abs(part1 - part2);

    }

    private int bfs(int start, Set<Integer>[] set, boolean[] visited) {
        int count = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Integer curr = queue.poll();
            visited[curr] = true;
            for (Integer linked : set[curr]) {
                if (!visited[linked]) {
                    queue.add(linked);
                    count++;
                }
            }
        }
        return count;
    }

    private Set<Integer>[] initSet(int n) {
        Set[] set = new HashSet[n];
        for (int i = 0; i < n; i++) {
            set[i] = new HashSet();
        }
        return set;
    }
}
