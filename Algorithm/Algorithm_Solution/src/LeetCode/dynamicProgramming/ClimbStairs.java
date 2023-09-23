package LeetCode.dynamicProgramming;

public class ClimbStairs {
    // https://leetcode.com/problems/climbing-stairs/
    public static void main(String[] args) {
        System.out.println(new ClimbStairs().climbStairs(4));
    }

    public int climbStairs(int n) {
        int[] bucket = new int[n + 1];
        bucket[0] = 1;
        bucket[1] = 1;
        dp(n, bucket);
        return bucket[n];
    }

    public void dp(int n, int[] bucket) {
        for (int i = 2; i <= n; i++) {
            bucket[i] = bucket[i - 1] + bucket[i - 2];
        }
    }
}
