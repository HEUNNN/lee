package LeetCode.backTracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        int n = 2;
        List<String> strings = new GenerateParentheses().generateParenthesis(n);
        for (String string : strings) {
            System.out.println(string + " ");
        }
    }
    // BackTracking와 Recursion는 유사하다.
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        String str = "";
        process(n, 0, 0, str, result); // Recursion
        return result;
    }

    // numClose > numOpen: invalid
    public void process(int n, int numOpen, int numClose, String str, List<String> list) {
        if (numOpen == n && numClose == n) {
            list.add(str);
            return;
        }

        // add open bracket
        if (numOpen < n) {
            process(n, numOpen + 1, numClose, str + "(", list);
        }
        // add close bracket
        if (numOpen > numClose) {
            process(n, numOpen, numClose + 1, str + ")", list);
        }
    }
}
