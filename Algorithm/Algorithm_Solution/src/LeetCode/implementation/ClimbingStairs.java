package LeetCode.implementation;

public class ClimbingStairs {
    // https://leetcode.com/problems/climbing-stairs/description/
    // 피보나치수열
    // f(n) = f(n-1) + f(n-2)
    // 그냥 재귀 구현시 공간복잡도 O(1), 시간 복잡도 O(2^n)
    // f(2) = 2, f(3) = 3 등을 미리 계산해서 정해둠(다이나믹 프로그래밍) → 공간 복잡도 O(n), 시간 복잡도 O(n)
    // 결국 점화식을 찾는 것이 중요하다.
    public static void main(String[] args) {
        int i = new ClimbingStairs().climbStairs(3);
        System.out.println(i);
    }
    public int climbStairs(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1; // n == 1이면 dynamic 길이가 2이므로, dynamic[2]를 채울 때 에러가 난다.

        int[] dynamic = new int[n + 1]; // index가 1인 곳 부터 채우기 위함
        dynamic[1] = 1;
        dynamic[2] = 2;
        for (int i = 3; i <= n; i++) {
            dynamic[i] = dynamic[i - 1] + dynamic[i - 2];
        }
        return dynamic[n];

    }
}
