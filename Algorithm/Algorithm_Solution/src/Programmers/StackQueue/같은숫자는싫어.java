package Programmers.StackQueue;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class 같은숫자는싫어 {

    public static void main(String[] args) {
       int[] arr1 = {1, 1, 3, 3, 0, 1, 1};
       int[] arr2 = {4, 4, 4, 3, 3};
        int[] answer = new 같은숫자는싫어().solution1(arr2);
        for (int i : answer) {
            System.out.println(i);
        }
    }

    public int[] solution1(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (stack.size() == 0) {
                stack.push(arr[i]);
            } else if (stack.peek() != arr[i]) {
                stack.push(arr[i]);
            }
        }

        int[] result = new int[stack.size()];

        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;

    }

    public int[] solution2(int[] arr) {
        List<Integer> lists = new ArrayList<>();
        int prevInt;

        lists.add(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            prevInt = lists.get(lists.size() - 1);
            if (prevInt != arr[i]) {
                lists.add(arr[i]);
            }
        }

        int[] result = new int[lists.size()];
        for (int i = 0; i < lists.size(); i++) {
            result[i] = lists.get(i);
        }
        return result;
    }
}
