package LeetCode.stack;

import java.util.Stack;

public class ValidBracket {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (st.size() == 0 || st.pop() != '(') return false;
            } else if (c == '}') {
                if (st.size() == 0 || st.pop() != '{') return false;
            } else if (c == ']') {
                if (st.size() == 0 || st.pop() != '[') return false;
            } else { // open bracket
                st.push(c);
            }
        }
        return st.size() == 0;
    }
}
