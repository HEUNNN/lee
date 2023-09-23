package Programmers.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class DoublePriorityQueueV2 {
    public static void main(String[] args) {
        String[] operations1 = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        String[] operations2 = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        String[] operations3 = {"I 1", "I 2", "I 3", "I 4", "I 5", "D -1", "D -1", "D -1", "D -1"}; // (5, 5)
        int[] solution = new DoublePriorityQueueV2().solution(operations3);
    }

    public int[] solution(String[] arguments) {
        int[] answer = {0, 0};

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> reverse_pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < arguments.length; i++) {
            String temp[] = arguments[i].split(" ");
            switch (temp[0]) {
                case "I":
                    pq.add(Integer.parseInt(temp[1]));
                    reverse_pq.add(Integer.parseInt(temp[1]));
                    break;
                case "D":
                    if (pq.size() > 0) {
                        if (Integer.parseInt(temp[1]) == 1) {
                            // 최댓값 삭제
                            int max = reverse_pq.poll();
                            pq.remove(max);
                        } else {
                            // 최솟값 삭제
                            int min = pq.poll();
                            reverse_pq.remove(min);
                        }
                    }
                    break;
            }
        }

        if (pq.size() > 0) {
            answer[0] = reverse_pq.poll();
            answer[1] = pq.poll();
        }

        return answer;
    }
}
