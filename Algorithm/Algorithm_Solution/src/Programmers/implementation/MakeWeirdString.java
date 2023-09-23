package Programmers.implementation;

public class MakeWeirdString {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12930

    public static void main(String[] args) {
        String s = "try hello world";
        String solution = new MakeWeirdString().solution(s);
        System.out.println(solution + "!");
    }

    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        int idx = 0; // 공백별로 구분된 문자열의 index
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                idx = 0;
                sb.append(' ');
            } else {
                sb.append(idx++ % 2 == 0 ? Character.toUpperCase(c) : Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

}
