package Programmers.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoublePriorityQueueV1 {
    public static void main(String[] args) {
        String[] operations1 = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        String[] operations2 = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        String[] operations3 = {"I 1", "I 2", "I 3", "I 4", "I 5", "D -1", "D -1","D -1", "D -1"}; // (5, 5)
        int[] solution = new DoublePriorityQueueV1().solution(operations3);
    }

    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        List<Integer> list = new ArrayList<>();
        initList(operations, list);
        if (list.isEmpty()) return new int[]{0, 0};
        return new int[]{list.get(list.size() - 1), list.get(0)};
    }

    private void initList(String[] operations, List<Integer> list) {

        for (String operation : operations) {
            String[] split = operation.split(" ");
            String op = split[0];
            Integer num = Integer.parseInt(split[1]);
            if (op.equals("I")) {
                list.add(num);
                Collections.sort(list);
            } else if (num.equals(1) && list.size() > 0) {
                list.remove(list.size() - 1);
            } else if (num.equals(-1) && list.size() > 0) {
                list.remove(0);
            }
        }
    }
}
