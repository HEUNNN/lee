package sw_expert.d3;

import java.util.Scanner;

class MaxReward {
    static int max = Integer.MIN_VALUE;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            String number = sc.next();
            int change = sc.nextInt();
            char[] numbers = number.toCharArray();
            dfs(0, numbers, 0, change);
            System.out.println("#" + test_case + " " + max);
            max = Integer.MIN_VALUE;
        }
    }

    private static void dfs(int start, char[] numbers, int depth, int change) {
        if (depth == change) {
            max = Math.max(max, charsToInteger(numbers));
            return;
        }
        for (int i = start; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                swap(numbers, i, j);
                dfs(i, numbers, depth + 1, change);
                swap(numbers, j, i); // 복구
            }
        }
    }

    private static void swap(char[] target, int one, int two) {
        char tmp = target[one];
        target[one] = target[two];
        target[two] = tmp;
    }

    private static int charsToInteger(char[] numbers) {
        return Integer.parseInt(new String(numbers));
    }
}