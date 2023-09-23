package Programmers.implementation;

public class FindMidString {
    public static void main(String[] args) {
        String s = "abcde";
        String solution = new FindMidString().solution2(s);
        System.out.println(solution);
    }

    public String solution(String s) { // StringBuilder를 사용하여 속도를 빠르게 했음
        StringBuilder sb = new StringBuilder();
        String answer = "";
        int mid = s.length() / 2;
        if (s.length() % 2 == 0) {
            sb.append(s.charAt(mid - 1));
            sb.append(s.charAt(mid));
        } else {
            sb.append(s.charAt(mid));
        }
        return answer = sb.toString();
    }

    public String solution2(String s) {
        return s.substring((s.length() - 1) / 2, s.length() / 2 + 1); // s의 길이가 홀 수 일 때 (s.length() - 1) / 2 나 s.length() / 2 나 같다.
    }
}
