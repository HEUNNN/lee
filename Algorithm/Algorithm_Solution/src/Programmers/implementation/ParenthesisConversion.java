package Programmers.implementation;

public class ParenthesisConversion {
    // https://school.programmers.co.kr/learn/courses/30/lessons/60058

    public static void main(String[] args) {
        String p = "()))((()";
        String solution = new ParenthesisConversion().solution(p);
        System.out.println(solution);
    }

    public String solution(String p) {
        return findCorrectParenthesis(p);
    }

    private String findCorrectParenthesis(String p) {
        if (p.length() == 0 || isCorrectParenthesis(p)) return p;
        String u = p.substring(0, balancedParenthesisIndex(p));
        String v = p.substring(balancedParenthesisIndex(p));

        if (isCorrectParenthesis(u)) {
            return u + findCorrectParenthesis(v);
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append("(").append(findCorrectParenthesis(v)).append(")");

            for (int i = 1; i < u.length() - 1; i++) { // u의 양 끝을 제거
                if (u.charAt(i) == '(') {
                    sb.append(")");
                } else {
                    sb.append("(");
                }
            }
            return sb.toString();
        }
    }

    private boolean isCorrectParenthesis(String p) { // 올바른 괄호인지 찾는 메서드
        int open = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                open++;
            } else {
                if (open == 0) return false;
                open--;
            }
        }
        return open == 0;
    }

    private int balancedParenthesisIndex(String p) {
        int open = 0;
        int close = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            if (open == close) {
                return open + close;
            }
        }
        return p.length();
    }
}
