package Programmers.queue;

import java.util.*;

public class Printer {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42587
    public static void main(String[] args) {
        int[] priorities = {1, 1, 9, 1, 1, 1};
        int location = 0;
        int solution = new Printer().solution(priorities, location);
        System.out.println(solution);
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> priority = new PriorityQueue<>(Comparator.reverseOrder());
        for (int p : priorities) {
            priority.add(p);
        }

        while (!priority.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (priority.peek() == priorities[i]) {
                    answer++;
                    priority.poll();
                    if (i == location) return answer;
                }
            }
        }

        return answer;
    }
}
