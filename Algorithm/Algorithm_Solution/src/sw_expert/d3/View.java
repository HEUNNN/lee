package sw_expert.d3;

import java.util.Scanner;

class View {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;


        for (int test_case = 1; test_case <= T; test_case++) {
            int cnt = 0;
            int N = sc.nextInt();
            int[] buildings = new int[N];
            for (int i = 0; i < N; i++) {
                buildings[i] = sc.nextInt();
            }

            for (int i = 2; i < N - 2; i++) {
                int leftMax = Math.max(buildings[i - 1], buildings[i - 2]);
                int rightMax = Math.max(buildings[i + 1], buildings[i + 2]);
                if (buildings[i] > leftMax && buildings[i] > rightMax) {
                    cnt += buildings[i] - Math.max(leftMax, rightMax);
                }
            }
            System.out.println("#" + test_case + " " + cnt);
        }
    }
}