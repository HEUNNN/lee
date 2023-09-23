package Programmers.implementation;

public class SumOfTwoNumber {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12912
    public static void main(String[] args) {
        int a = 3;
        int b = 5;
        long solution = new SumOfTwoNumber().solution(a, b);
        System.out.println(solution);
    }

    public long solution(int a, int b) {
        long answer = 0;
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min <= max) {
            answer += min;
            min++;
        }
        return answer;
    }

    // 등차수열의 합 공식 사용한 풀이 → last와 first 사이가 큰 경우에는 시간 단축이 훨씬 좋음
    public long solution2(int a, int b) {
        int first = Math.min(a, b);
        int last = Math.max(a, b);
        // 등차수열 합 공식 : n(a + l) / 2
        // n은 첫번째항 a 부터 마지막항 l 까지의 개수를 의미
        return (long) (last - first + 1) * (first + last) / 2;
    }
}
