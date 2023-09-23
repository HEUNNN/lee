package Programmers.dfs;

public class TargetNumberV1 {
    public static void main(String[] args) {
        int[] numbers = new int[]{4, 1, 2, 1};
        int target = 4;
        int solution = new TargetNumberV1().solution(numbers, target);
        System.out.println(solution);
    }

    public int solution(int[] numbers, int target) {
        return dfs(numbers, 0, 0, target);
    }

    public int dfs(int[] numbers, int idx, int sum, int target) {
        if (idx == numbers.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }
        return dfs(numbers, idx + 1, sum + numbers[idx], target) + dfs(numbers, idx + 1, sum - numbers[idx], target);
    }

}