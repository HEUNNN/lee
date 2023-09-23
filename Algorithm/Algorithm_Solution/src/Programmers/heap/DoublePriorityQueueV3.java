package Programmers.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DoublePriorityQueueV3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DoublePriorityQueueV3().solution(new String[]{
                "I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"
        })));
        System.out.println(Arrays.toString(new DoublePriorityQueueV3().solution(new String[]{
                "I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"
        })));
    }

    public int[] solution(String[] operations) {
        PriorityQueue<Num> minPq = new PriorityQueue<>(Comparator.comparing((Num n) -> n.value));
        PriorityQueue<Num> maxPq = new PriorityQueue<>(Comparator.comparing((Num n) -> n.value).reversed());
        for (String operation : operations) {
            String[] split = operation.split(" ");
            String command = split[0];
            String numString = split[1];
            if (command.equals("I")) {
                Num num = new Num(Integer.parseInt(numString));
                minPq.add(num);
                maxPq.add(num);
            } else if (command.equals("D")) {
                clear(minPq);
                clear(maxPq);

                if (minPq.isEmpty() || maxPq.isEmpty()) {
                    continue;
                }

                if (numString.equals("-1")) {
                    Num poll = minPq.poll();
                    poll.deleted = true;
                } else {
                    Num poll = maxPq.poll();
                    poll.deleted = true;
                }
            }
        }

        clear(minPq);
        clear(maxPq);
        if (minPq.isEmpty() || maxPq.isEmpty()) {
            return new int[]{0, 0};
        }

        return new int[]{maxPq.poll().value, minPq.poll().value};
    }

    private void clear(PriorityQueue<Num> pq) {
        while (!pq.isEmpty() && pq.peek().deleted) {
            pq.poll();
        }
    }

    static class Num {
        int value;
        boolean deleted;

        public Num(int value) {
            this.value = value;
        }
    }

}
