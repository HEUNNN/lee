package Programmers.StackQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class FunctionDevelop {
    public static void main(String[] args) {
        int[] progresses1 = {93, 30, 55};
        int[] speeds1 = {1, 30, 5};
        int[] progresses2 = {95, 90, 99, 99, 80, 99};
        int[] speeds2 = {1, 1, 1, 1, 1, 1};

        int[] answer = new FunctionDevelop().soultion(progresses2, speeds2);
        for (int num : answer) {
            System.out.println(num);
        }
    }

    public int[] soultion(int[] progresses, int[] speeds) {
        int[] diff = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            diff[i] = (int) Math.ceil((100 - progresses[i]) / (speeds[i] * 1.0));
        }

        Stack<Integer> bucket = new Stack<>();
        Stack<Integer> tmpResult = new Stack<>();

        bucket.add(diff[0]);
        tmpResult.add(1);

        for (int i = 1; i < diff.length; i++) {
            if (bucket.peek() >= diff[i]) {
                int prev = tmpResult.pop();
                tmpResult.add(prev + 1);
            } else {
                tmpResult.add(1);
            }
            bucket.add(diff[i]);
        }

        int[] answer = new int[tmpResult.size()];
        for (int i = tmpResult.size() - 1; i >= 0; i--) {
            answer[i] = tmpResult.pop();
        }
        return answer;
    }

    public int[] solution2(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            int tmp = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            if (!q.isEmpty() && q.peek() < tmp) {
                list.add(q.size());
                q.clear();
            }
            q.offer(tmp);
        }

        list.add(q.size());

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
