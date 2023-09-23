package Programmers.stack;

import java.util.Stack;

public class CorrectBracket {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12909
    public static void main(String[] args) {
        String s = "(())()";
        boolean solution = new CorrectBracket().solution(s);
        System.out.println(solution);
    }

    boolean solution(String s) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push('(');
            } else {
                if (st.isEmpty()) return false; // '(' 없이 ')'만 들어온 경우
                st.pop();
            }
        }
        if (!st.isEmpty()) return false;
        return true;
    }

    // Stack 사용 X → 속도가 더 빠르다.
    boolean solution2(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) return false;
        }
        if (count == 0) return true;
        return false;
    }
}
