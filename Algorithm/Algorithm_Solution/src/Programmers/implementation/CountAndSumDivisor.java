package Programmers.implementation;

import java.util.HashSet;
import java.util.Set;

public class CountAndSumDivisor {
    // https://school.programmers.co.kr/learn/courses/30/lessons/77884
    // 약수의 개수와 덧셈

    public static void main(String[] args) {
        int number = 9;
        int number2 = 11;
        int number3 = 16;
        int result = new CountAndSumDivisor().solution(24, 27);
        System.out.println(Math.sqrt(8));
        System.out.println(result);

    }

    public int solution(int left, int right) {
        int answer = 0;
        for (int i = left; i <= right; i++) {
            if (findDivisorsNumber(i) % 2 == 0) {
                answer += i;
            } else {
                answer -= i;
            }
        }
        return answer;
    }

    public int solution2(int left, int right) {
        int answer = 0;
        // 제곱수인 경우 약수의 개수: 홀수, 제곱수가 아닌 경우 약수의 개수: 짝수
        // Math.sqrt(a) 는 double type의 결과가 나온다.
        for (int i = left; i <= right; i++) {
            if (i % Math.sqrt(i) == 0) { // 제곱수
                answer -= i;
            } else {
                answer += i;
            }
        }
        return answer;
    }

    private int findDivisorsNumber(int number) { // 약수 개수 반환
        Set<Integer> set = new HashSet<>();
        int tmp = 1;
        while (tmp <= Math.sqrt(number)) {
            if (number % tmp == 0) {
                set.add(tmp);
                set.add(number / tmp);
            }
            tmp++;
        }
        return set.size();
    }
}
