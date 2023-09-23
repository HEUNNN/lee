package LeetCode.implementation;

import java.util.Stack;

public class ValidParentheses {
    // 올바른 괄호
    // Leet Code https://leetcode.com/problems/valid-parentheses/description/
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') st.push(c);
            else if (c == ')') { // 닫는 괄호를 만나면 pop 해서 비교후 false인지 판단
                if (st.size() == 0 || st.pop() != '(') return false;
            } else if (c == '}') {
                if (st.size() == 0 || st.pop() != '{') return false;
            } else if (c == ']') {
                if (st.size() == 0 || st.pop() != '[') return false;
            } else {
                return false; // 아예 이상한 문자
            }
        }
        return st.size() == 0;
    }
}
