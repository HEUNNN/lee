package Programmers.implementation;

public class CountPAndY {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12916
    public static void main(String[] args) {
        String s = "abcd";
        boolean solution = new CountPAndY().solution(s);
        System.out.println(solution);
    }
    boolean solution(String s) {
        int p = 0;
        int y = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'p' || s.charAt(i) == 'P') {
                p++;
            }
            if (s.charAt(i) == 'y' || s.charAt(i) == 'Y') {
                y++;
            }
        }
        return p == y;
    }
}
