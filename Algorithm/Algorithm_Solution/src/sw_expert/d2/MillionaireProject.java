package sw_expert.d2;

import java.util.Scanner;

public class MillionaireProject {
    // https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5LrsUaDxcDFAXc&categoryId=AV5LrsUaDxcDFAXc&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=2&pageSize=10&pageIndex=1
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int[] arr = new int[N];
            long result = 0;
            int max = 0;
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }
            for (int i = N - 1; i >= 0; i--) {
                if (max < arr[i]) max = arr[i];
                result += (max - arr[i]);
            }
            System.out.printf("#%d %d\n", test_case, result);
        }
    }
}
