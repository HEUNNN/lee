package Programmers.heap;

import java.util.PriorityQueue;

public class ScovilleIndex {
    public static void main(String[] args) {
        int[] scoville = {1, 1};
        int K = 7;
        int solution = new ScovilleIndex().solution(scoville, K);

        System.out.println(solution);
    }
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : scoville) {
            pq.add(i);
        }
        while (pq.size() >= 2) {
            if (pq.peek() < K) {
                pq.add(pq.poll() + (pq.poll() * 2));
                answer++;
            } else {
                pq.poll();
            }
        }
        if (pq.poll() < K) return -1;
        return answer;
    }
}
