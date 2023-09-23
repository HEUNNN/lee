package Programmers.string;

public class StringToInteger {
    public static void main(String[] args) {
        int solution = new StringToInteger().solution2("-1230");
        System.out.println(solution);
    }

    public int solution(String s) {
        if (s.startsWith("-")) {
            return -(Integer.parseInt(s.substring(1)));
        } else if (s.startsWith("+")) {
            return Integer.parseInt(s.substring(1));
        } else {
            return Integer.parseInt(s);
        }
    }

    // Integer.parseInt 사용하지 않고 숫자로 변환하기
    public int solution2(String s) {
        boolean sign = true; // + 인 경우 true, - 인 경우 false
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-') {
                sign = false;
            } else if (c != '+') {
                result = result * 10 + (c - '0');
            }
        }
        return sign ? 1 : -1 * result;
    }
}
