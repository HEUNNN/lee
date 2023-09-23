package Programmers.dfs;

public class TargetNumberV2 {
    // target number review
    // https://school.programmers.co.kr/learn/courses/30/lessons/43165
    static int result = 0;

    public static void main(String[] args) {
        int solution = new TargetNumberV2().solution(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println(solution);

    }

    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, target, 0);
        return result;
    }

    private void dfs(int[] numbers, int idx, int target, int count) {
        if (idx == numbers.length) {
            if (count == target) {
                result++;
            }
            return;
        }
        dfs(numbers, idx + 1, target, count + numbers[idx]);
        dfs(numbers, idx + 1, target, count - numbers[idx]);
    }
}
