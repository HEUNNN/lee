package Programmers.greedy;

import java.util.Stack;

public class MakeBigNumber {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42883
    public static void main(String[] args) {
        String number = "4321";
        int k = 1;
        String solution = new MakeBigNumber().solution(number, k);
        System.out.println(solution);
    }

    public String solution(String number, int k) {
        Stack<Character> bucket = new Stack<>();
        char[] result = new char[number.length() - k];
        int removed = k;
        for (int i = 0; i < number.length(); i++) {
            while (!bucket.isEmpty() && removed > 0 && bucket.peek() < number.charAt(i)) {
                bucket.pop();
                removed--;
            }

            bucket.add(number.charAt(i));
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = bucket.get(i);
        }
        return String.valueOf(result);
    }

}
