package Programmers.implementation;

public class SumOfDivisor {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12928
    public static void main(String[] args) {
        int solution = new SumOfDivisor().solution2(12);
        System.out.println(solution);
    }

    public int solution(int n) {
        int answer = 0;
        if (n == 1) return 1;
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                answer += i;
                if (n / i != i) {
                    answer += n / i;
                }
            }
        }
        return answer;
    }

    public int solution2(int n) {
        int answer = 0;
        for (int i = 1; i <= n / 2; i++) { // n이 짝수일 때 2와 곱해지는 몫이 약수들 중에서 두 번째로 큰 약수가 된다. 가장 큰 수는 n이다. 홀수인 경우 첫 번째로 가장 큰 약수 n 다음으로 큰 약수는 n/2한 수보다 작다.
            if (n % i == 0) answer += i;
        }
        return answer + n;
    }
}
