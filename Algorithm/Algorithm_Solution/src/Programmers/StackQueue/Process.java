package Programmers.StackQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Process {
    public static void main(String[] args) {
        int[] priorities1 = {2, 1, 3, 2};
        int[] priorities2 = {1, 1, 9, 1, 1, 1};
        int solution = new Process().solution(priorities1, 3);
        System.out.println(solution);
    }

    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        // 중복된 priority도 pq에 들어간다.

        for (int priority : priorities) {
            priorityQueue.add(priority);
        }

        while (!priorityQueue.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (priorityQueue.peek() == priorities[i]) {
                    answer++;
                    priorityQueue.poll();
                    if (i == location) return answer;
                }
            }
        }

        return answer;
    }
}
