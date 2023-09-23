package Programmers.heap;

import java.util.*;

public class 이중우선순위큐 {
    public static void main(String[] args) {
        String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        new 이중우선순위큐().solution(operations);
    }

    public void solution(String[] operations) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        Queue<String> operationsQueue = arrToQueue(operations);

        while (operationsQueue.size() >= 1) {
            String oper = operationsQueue.poll();
            if (oper.equals("D") && priorityQueue.size() >= 1) {
                
            } else if (oper.equals("D-") && priorityQueue.size() >= 1) {

            } else { // I
                priorityQueue.add(Integer.parseInt(oper));
            }

        }
    }

    private Queue<String> arrToQueue(String[] operations) {
        Queue<String> queue = new LinkedList<>();
        for (String operation : operations) {
            String[] elem = operation.split(" ");
            if (elem[0].equals("I")) {
                queue.add(elem[1]);
                continue;
            }
            if (Integer.parseInt(elem[1]) == -1) {
                queue.add("D");
            } else {
                queue.add("D-");
            }
        }
        return queue;
    }

}
