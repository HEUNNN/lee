package Programmers.heap;

import java.util.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DiskController {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42627
    //https://velog.io/@davidko/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%94%94%EC%8A%A4%ED%81%AC-%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%ACjava
    public static void main(String[] args) {
        int[][] jobs = new int[][]{{1, 9}, {0, 3}, {2, 6}};
        int solution = new DiskController().solution(jobs);
        System.out.println(solution);
    }

    public int solution(int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0])); // (o1, o2) -> o1[0] - o2[0]
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1])); // (o1, o2) -> o1[1] - o2[1]

        int idx = 0;
        int count = 0;
        int sum = 0;
        int end = 0;

        while (count < jobs.length) {
            while (idx < jobs.length && jobs[idx][0] <= end) {
                queue.add(jobs[idx++]);
            }

            if (queue.isEmpty()) {
                end = jobs[idx][0];
            } else {
                int[] curr = queue.poll();
                sum += (end - curr[0]) + curr[1];
                end += curr[1];
                count++;
            }
        }

        return sum / jobs.length;
    }

}
