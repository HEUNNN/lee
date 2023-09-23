package Programmers.StackQueue;

public class CorrectParentheses {
    public static void main(String[] args) {
        System.out.println(new CorrectParentheses().solution("(()("));
    }

    public boolean solution(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) return false;
        }
        return count == 0;
    }

}
