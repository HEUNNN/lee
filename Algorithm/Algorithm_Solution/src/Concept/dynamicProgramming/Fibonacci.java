package Concept.dynamicProgramming;

public class Fibonacci {
    // 피보나치 수열을 DP 탑다운(하향식) 방식을 적용하여 구해본다. → 탑다운 방식은 재귀를 주로 사용
    // 피보나치 수열을 DB 보텁업(상향식) 방식을 적용하여 구해본다. → 보텁업 방식은 for문을 주로 사용
    public static void main(String[] args) {
        // top down
        long[] dp1 = new long[101];
        long result1 = new Fibonacci().topDown(dp1, 20);
        System.out.println(result1);

        // bottom up
        long[] dp2 = new long[101];
        long result2 = new Fibonacci().bottomUp(dp2, 20);
        System.out.println(result2);

    }

    private long topDown(long[] dp, int number) { // Fibonacci topdown
        if (number == 1 || number == 2) return 1; // 종료 조건
        if (dp[number] != 0) { // 이미 계산된적이 있어 dp 배열에 Memoization이 되어 있는 경우
            return dp[number];
        }
        dp[number] = topDown(dp, number - 1) + topDown(dp, number - 2); // 아직 계산하지 않은 문제라면 점화식에 따라 피보나치 결과 계산 후 메모
        return dp[number];
    }

    private long bottomUp(long[] dp, int number) {
        dp[1] = 1;
        dp[2] = 1;

        // 피보나치 함수 반복문으로 미리 각 숫자의 피보나치 수열을 구해두고 사용
        for (int i = 3; i <= 50; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[number];
    }
}
