package Programmers.queue;

import java.util.*;


public class MakeQueueSame {
    // https://school.programmers.co.kr/learn/courses/30/lessons/118667


    public static void main(String[] args) {
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};
        int solution = new MakeQueueSame().solution(queue1, queue2);
        System.out.println(solution);
    }

    public int solution(int[] queue1, int[] queue2) {
        int count = 0;
        int n = queue1.length;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        qInit(q1, queue1);
        qInit(q2, queue2);

        boolean flag = false;

        for (int i = 0; i < n * 2; i++) {
            long sum1 = qSum(q1);
            long sum2 = qSum(q2);

            if ((sum1 + sum2) % 2 != 0) return -1;

            if (sum1 == sum2) {
                flag = true;
            } else if (sum1 > sum2) {
                Integer poll = q1.poll();
                if (poll > (sum1 + sum2) / 2) return -1;
                q2.add(poll);
                count++;
            } else {
                Integer poll = q2.poll();
                if (poll > (sum1 + sum2) / 2) return -1;
                q1.add(poll);
                count++;
            }
        }


        return count;
    }

    private void qInit(Queue<Integer> q, int[] qArr) {
        for (int i : qArr) {
            q.add(i);
        }
    }

    private long qSum(Queue<Integer> q) {
        long sum = 0;
        for (int i = 0; i < q.size(); i++) {
            Integer tmp = q.poll();
            sum += tmp;
            q.add(tmp);
        }
        return sum;
    }

}
