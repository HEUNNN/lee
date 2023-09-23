package Programmers.heap;

import java.util.PriorityQueue;

public class MoreSpicy {
    public static void main(String[] args) {
        MoreSpicy MoreSpicy = new MoreSpicy();
        int[] arr = {1, 3, 2, 9, 10, 12};
        int solution = MoreSpicy.solution(arr, 7);
        System.out.println(solution);
    }
    public int solution(int[] scoville, int k) {
        int answer = 0;
        PriorityQueue<Integer> priorityQueue = makePriorityQueue(scoville);

        while (priorityQueue.size() >= 2) {
            if (priorityQueue.peek() >= k) break;
            priorityQueue.add(scovilleCalculate(priorityQueue.poll(), priorityQueue.poll()));
            answer++;
        }

        if (priorityQueue.size() == 1 && priorityQueue.peek() < k) {
            return -1;
        }
        return answer;
    }

    private PriorityQueue<Integer> makePriorityQueue(int[] scoville) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            priorityQueue.add(scoville[i]);
        }
        return priorityQueue;
    }

    private int scovilleCalculate(int firstLow, int secondLow) {
        return firstLow + (secondLow * 2);
    }
}
