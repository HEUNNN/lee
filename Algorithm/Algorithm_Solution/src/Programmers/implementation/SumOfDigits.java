package Programmers.implementation;

public class SumOfDigits {
    // 자릿수 더하기
    // https://school.programmers.co.kr/learn/courses/30/lessons/12931
    public static void main(String[] args) {
        int n = 1;
        int result = new SumOfDigits().solution(n);
        System.out.println(result);

    }

    public int solution(int n) {
        int answer = 0;
        String[] digitArr = String.valueOf(n).split("");
        for (String s : digitArr) {
            answer += Integer.parseInt(s);
        }
        return answer;
    }

    public int solution2(int n) {
        int answer = 0;
        while (true) {
            answer += n % 10;
            if (n < 10) break;
            n /= 10;
        }
        return answer;
    }
}
