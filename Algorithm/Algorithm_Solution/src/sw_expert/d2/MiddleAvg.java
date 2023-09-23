package sw_expert.d2;

import java.util.Scanner;


class MiddleAvg {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int sum = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < 10; i++) {
                int num = sc.nextInt();
                sum += num;
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
            sum -= min;
            sum -= max;
            int result = (int) Math.round(sum / 8.0);
            System.out.println("#" + test_case + " " + result);
        }
    }
}