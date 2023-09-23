package Programmers.string;

public class CaesarCipher {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12926
    public static void main(String[] args) {
        String result = new CaesarCipher().solution("AaZz", 25);
        System.out.println(result);
    }

    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                sb.append(c);
            } else if (c >= 65 && c <= 90) {
                // 대문자
                c += n;
                if (c >= 91) {
                    c -= 26;
                }
                sb.append(c);
            } else {
                // 소문자
                c += n;
                if (c >= 123) {
                    c -= 26;
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
